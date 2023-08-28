package top.oneyoung.base.classload;

/**
 * ClassLoadTest
 *
 * @author oneyoung
 * @since 2023-08-26 21:03
 */

public class ClassLoadTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Object.class.getClassLoader();
        System.out.println(classLoader);
        ClassLoader classLoader1 = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader1);
        System.out.println(classLoader1.getParent());
        System.out.println(classLoader1.getParent().getParent());
    }
}
