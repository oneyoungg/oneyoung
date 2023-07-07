package oneyoung.designpattern.singleton;

/**
 * MainTest
 * <p>
 * 2020/8/9 009 22:29
 *
 * @author oneyoung
 */

public class MainTest {
    public static void main(String[] args) {
        new Thread(() -> System.out.println( SingletonObject4.getInstance()+Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println( SingletonObject4.getInstance()+Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println( SingletonObject4.getInstance()+Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println( SingletonObject4.getInstance()+Thread.currentThread().getName())).start();
    }
}
