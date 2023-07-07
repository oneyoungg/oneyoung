package top.oneyoung.dynamic;


import top.oneyoung.dynamic.open.ByteArrayJavaFileManager;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;

/**
 * TestDynamicCompilation
 *
 * @author oneyoung
 * @since 2022/4/6 11:26
 */
public class TestDynamicCompilation {
    public static void main(String[] args) throws Exception {
//        fromJavaFile();
//        fromJavaSourceCode();
        fromJavaSourceToByteArray1();
    }


    public static void fromJavaFile() throws Exception {
        // 获取Javac编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取文件管理器：负责管理类文件的输入输出
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        // 获取要被编译的Java源文件
        File file = new File("/Users/oneyoung/oneyoung/project/my/code/src/main/java/top/oneyoung/dynamic/TestHello.java");
        // 通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个JavaFileObject
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
        // 生成编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
        // 执行编译任务
        Boolean call = task.call();
        if (call) {
            Class<?> o = loadClassFromDisk("/Users/oneyoung/oneyoung/project/my/code/src/main/java/top/oneyoung/dynamic/TestHello.class");
            Method main = o.getMethod("main", String[].class);
            main.invoke(null, new Object[]{new String[]{}});
        }
    }

    public static void fromJavaSourceCode() {
        // 获取Javac编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取文件管理器：负责管理类文件的输入输出
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        // 通过源代码字符串获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个JavaFileObject
        SourceJavaFileObject javaFileObject = new SourceJavaFileObject("TestHello", "public class TestHello { public static void main(String[] args) { System.out.println(\"Hello World\"); } }");
        // 生成编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singleton(javaFileObject));
        // 执行编译任务
        Boolean call = task.call();

    }

    public static void fromJavaSourceToByteArray() {
        // 获取Javac编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取文件管理器：负责管理类文件的输入输出
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        // 通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个JavaFileObject
        SourceJavaFileObject javaFileObject = new SourceJavaFileObject("TestHello", "public class TestHello { public static void main(String[] args) { System.out.println(\"Hello World\"); } }");

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singletonList(javaFileObject));
        // 执行编译任务
        task.call();
    }

    public static void fromJavaSourceToByteArray1() throws Exception {
        // 获取Javac编译器对象
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取文件管理器：负责管理类文件的输入输出
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        ByteArrayJavaFileManager byteArrayJavaFileManager = new ByteArrayJavaFileManager(fileManager);
        // 通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个JavaFileObject
        JavaFileObject javaFileObject = new SourceJavaFileObject("TestHello", "public class TestHello { public static void say(String args) { System.out.println(args); } }");

        JavaCompiler.CompilationTask task = compiler.getTask(null, byteArrayJavaFileManager, null, null, null, Collections.singletonList(javaFileObject));
        // 执行编译任务
        Boolean call = task.call();
        if (Boolean.TRUE.equals(call)) {
//            Map<String, byte[]> allBuffers = myJavaFileManager.getAllBuffers();
//            byte[] testHellos = allBuffers.get("TestHello");
            byte[] testHellos = byteArrayJavaFileManager.getBytesByClassName("TestHello");
            Class<ClassLoader> classLoaderClass = ClassLoader.class;
            Method defineClass = classLoaderClass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            defineClass.setAccessible(true);
            Object invoke = defineClass.invoke(TestHello.class.getClassLoader(), testHellos, 0, testHellos.length);
            Class clazz = (Class) invoke;
            clazz.getMethod("say", String.class).invoke(null, "你好");

        }
    }

    public static Class<?> loadClassFromDisk(String path) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // defineClass 为 ClassLoader 类的一个方法，用于加载类
        // 但是这个方法是 protected 的，所以需要通过反射的方式获取这个方法的权限
        Class<ClassLoader> classLoaderClass = ClassLoader.class;
        Method defineClass = classLoaderClass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        defineClass.setAccessible(true);
        // 读取文件系统的 file 为 byte 数组
        File file = new File(path);
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 执行 defineClass 方法 返回 Class 对象
        return (Class<?>) defineClass.invoke(Thread.currentThread().getContextClassLoader(), bytes, 0, bytes.length);
    }
}
