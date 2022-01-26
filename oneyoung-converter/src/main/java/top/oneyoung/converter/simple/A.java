package top.oneyoung.converter.simple;

/**
 * @author oneyoung
 * @since 2020/1/15 22:37
 */
public class A extends D {
    private C c;

    private String str;

    public String getStr() {
        return str;
    }

    public A setStr(String str) {
        this.str = str;
        return this;
    }

    public C getC() {
        return c;
    }

    public A setC(C c) {
        this.c = c;
        return this;
    }
}
