package top.oneyoung.designpattern.strategy;

/**
 * Demo
 *
 * @author oneyoung
 * @since 2023/7/13 17:44
 */
public class Demo {
    public static void main(String[] args) {
        Context context = new Context(new OneStrategy());
        context.execute();
        context = new Context(new TwoStrategy());
        context.execute();
    }
}
