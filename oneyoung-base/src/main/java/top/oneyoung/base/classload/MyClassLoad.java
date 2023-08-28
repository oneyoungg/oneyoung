package top.oneyoung.base.classload;

/**
 * MyClassLoad
 *
 * @author oneyoung
 * @since 2023-08-27 08:47
 */

public class MyClassLoad extends ClassLoader {

    private String classPath;

    public MyClassLoad(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
