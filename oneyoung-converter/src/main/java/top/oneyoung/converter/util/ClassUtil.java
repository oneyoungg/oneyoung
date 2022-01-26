package top.oneyoung.converter.util;


import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.oneyoung.converter.model.ClassInfo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static java.util.stream.Collectors.toMap;

/**
 * @author oneyoung
 * @date 2020/05/9
 */
public final class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    private static final ConcurrentHashMap<Class<?>, ClassInfo> CLASS_INFO_MAP = new ConcurrentHashMap<>();

    private static final String JAR = "jar";
    private static final String FILE = "file";
    private static final String REGEX = "%20";
    private static final String CLASS = ".class";
    private static final String DEL = ".";
    private static final String BACKLASH = "/";
    private static final String BLANK = " ";
    private static final String $ = "$";

    private ClassUtil() {
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = System.getSecurityManager() == null
                ? Thread.currentThread().getContextClassLoader()
                : AccessController.doPrivileged((PrivilegedAction<ClassLoader>) () -> Thread.currentThread().getContextClassLoader());

        if (classLoader == null) {
            LOGGER.error("get class loader error, class loader is null , current thread is {}", Thread.currentThread());
            classLoader = ClassUtil.class.getClassLoader();
        }

        return classLoader;
    }

    /**
     * @param className
     * @param isInitialized 是否执行类的初始化，静态代码块
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, false);
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            // 得到指定包名下面的资源
            String basePackagePath = packageName.replace(DEL, BACKLASH);
            Enumeration<URL> urls = getClassLoader().getResources(basePackagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (FILE.equals(protocol)) {
                        // 20%是URL的空格，将其代替，SUN公司也说明了这是一个BUG
                        String packagePath = url.getPath().replaceAll(REGEX, BLANK);
                        addFileClass(classSet, packagePath, packageName);
                    } else if (JAR.equals(protocol)) {
                        addJarClass(classSet, url, basePackagePath);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addJarClass(Set<Class<?>> classSet, URL url, String basePackagePath) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        if (jarURLConnection != null) {
            JarFile jarFile = jarURLConnection.getJarFile();
            if (jarFile != null) {
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = jarEntries.nextElement();
                    String jarEntryName = jarEntry.getName();
                    if (jarEntryName.startsWith(basePackagePath) && jarEntryName.endsWith(CLASS) && !jarEntryName.contains($)) {
                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(DEL)).replaceAll(BACKLASH, DEL);
                        doAddClass(classSet, className);
                    }
                }
            }
        }
    }

    private static void addFileClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> file.isFile() && file.getName().endsWith(CLASS) && !file.getName().contains($) || file.isDirectory());
        StringBuilder stringBuilder = new StringBuilder();
        if (ArrayUtils.isEmpty(files)) {
            return;
        }
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf(DEL));
                if (StringUtil.isNotEmpty(className)) {
                    className = stringBuilder.append(packageName).append(DEL).append(className).toString();
                    stringBuilder.setLength(0);
                    doAddClass(classSet, className);
                }
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(subPackagePath)) {
                    subPackagePath = stringBuilder.append(packagePath).append(BACKLASH).append(subPackagePath).toString();
                    stringBuilder.setLength(0);
                    String subPackageName = fileName;
                    if (StringUtil.isNotEmpty(packageName)) {
                        subPackageName = stringBuilder.append(packageName).append(DEL).append(subPackageName).toString();
                        stringBuilder.setLength(0);
                    }
                    addFileClass(classSet, subPackagePath, subPackageName);
                }
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

    public static ClassInfo getClassInfo(Class<?> cls) {
        return CLASS_INFO_MAP.containsKey(cls) ? CLASS_INFO_MAP.get(cls) : generateClassInfo(cls);
    }

    private static ClassInfo generateClassInfo(Class<?> cls) {
        ClassInfo classInfo = new ClassInfo();
        List<Field> fields = ReflectionUtil.getFields(cls);
        List<Method> methods = Arrays.asList(cls.getDeclaredMethods());
        classInfo.setFields(fields);
        classInfo.setMethods(methods);
        classInfo.setFieldMap(fields.stream().collect(toMap(Field::getName, Function.identity())));
        CLASS_INFO_MAP.put(cls, classInfo);
        return classInfo;
    }
}
