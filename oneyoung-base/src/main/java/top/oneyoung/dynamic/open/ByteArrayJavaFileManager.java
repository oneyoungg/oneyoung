package top.oneyoung.dynamic.open;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import javax.tools.JavaFileObject.Kind;
import java.io.*;
import java.net.URI;
import java.util.*;

/**
 * @author oneyoung
 */
public class ByteArrayJavaFileManager implements JavaFileManager {
    private static final Logger LOG = LoggerFactory.getLogger(ByteArrayJavaFileManager.class);


    private final StandardJavaFileManager fileManager;

    /**
     * synchronizing due to ConcurrentModificationException
     */
    private final Map<String, ByteArrayOutputStream> buffers = Collections.synchronizedMap(new LinkedHashMap<>());

    public ByteArrayJavaFileManager(StandardJavaFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return fileManager.getClassLoader(location);
    }

    @Override
    public synchronized Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse) throws IOException {
        return fileManager.list(location, packageName, kinds, recurse);
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        return fileManager.inferBinaryName(location, file);
    }

    @Override
    public boolean isSameFile(FileObject a, FileObject b) {
        return fileManager.isSameFile(a, b);
    }

    @Override
    public synchronized boolean handleOption(String current, Iterator<String> remaining) {
        return fileManager.handleOption(current, remaining);
    }

    @Override
    public boolean hasLocation(Location location) {
        return fileManager.hasLocation(location);
    }

    @Override
    public JavaFileObject getJavaFileForInput(Location location, String className, Kind kind) throws IOException {

        if (location == StandardLocation.CLASS_OUTPUT) {
            boolean success;
            final byte[] bytes;
            synchronized (buffers) {
                success = buffers.containsKey(className) && kind == Kind.CLASS;
                bytes = buffers.get(className).toByteArray();
            }
            if (success) {

                return new SimpleJavaFileObject(URI.create(className), kind) {
                    @Override
                    public InputStream openInputStream() {
                        return new ByteArrayInputStream(bytes);
                    }
                };
            }
        }
        return fileManager.getJavaFileForInput(location, className, kind);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, final String className, Kind kind, FileObject sibling) {
        return new SimpleJavaFileObject(URI.create(className), kind) {
            @Override
            public OutputStream openOutputStream() {
                // 字节输出流用与FileManager输出编译完成的字节码文件为字节数组
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                // 将每个需要加载的类的输出流进行缓存
                buffers.putIfAbsent(className, bos);
                return bos;
            }
        };
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        return fileManager.getFileForInput(location, packageName, relativeName);
    }

    @Override
    public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
        return fileManager.getFileForOutput(location, packageName, relativeName, sibling);
    }

    @Override
    public void flush() {
        // Do nothing
    }

    @Override
    public void close() throws IOException {
        fileManager.close();
    }

    @Override
    public int isSupportedOption(String option) {
        return fileManager.isSupportedOption(option);
    }

    public void clearBuffers() {
        buffers.clear();
    }


    public Map<String, byte[]> getAllBuffers() {
        Map<String, byte[]> ret = new LinkedHashMap<>(buffers.size() * 2);
        Map<String, ByteArrayOutputStream> compiledClasses = new LinkedHashMap<>(ret.size());
        synchronized (buffers) {
            compiledClasses.putAll(buffers);
        }
        compiledClasses.forEach((k, v) -> ret.put(k, v.toByteArray()));
        return ret;
    }

    public byte[] getBytesByClassName(String className) {
        return buffers.get(className).toByteArray();
    }
}