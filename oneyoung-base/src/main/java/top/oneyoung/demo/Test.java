package top.oneyoung.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test
 *
 * @author oneyoung
 * @since 2023/7/14 10:15
 */
public class Test {

    private volatile int a, b;
    private int x, y;
    public void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread t2 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            b = 2;
            y = a;
        });
        String ab = "";
        ab.intern();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.getAndIncrement();
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println("x = " + x + "  x == 2 " + (x == 2));
        System.out.println("y = " + y + "  y == 1 " + (y == 1));
    }


    public static void main(String[] args) throws InterruptedException {
        new Test().test();
    }
}
