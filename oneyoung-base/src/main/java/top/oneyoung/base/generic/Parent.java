package top.oneyoung.base.generic;

/**
 * Parent
 *
 * @author oneyoung
 * @since 2023/8/23 17:35
 */
public class Parent<T> {

    public <T, F> T getStatic(T f) {
        return f;
    }

    public T get() {
        return null;
    }

    public static void main(String[] args) {
        Parent<String> parent = new Parent<>();
        Object aStatic = parent.getStatic("1");
        System.out.println(aStatic.getClass());
    }
}
