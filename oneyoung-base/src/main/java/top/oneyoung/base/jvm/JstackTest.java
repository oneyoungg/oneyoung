package top.oneyoung.base.jvm;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * JstackTest
 *
 * @author oneyoung
 * @since 2023-08-27 14:16
 */

public class JstackTest {


    private static final Executor EXECUTORS = Executors.newFixedThreadPool(10);

    public static Object lock = new Object();

    public static long num;
    public static void main(String[] args) {
        EXECUTORS.execute(() -> {
            synchronized (lock) {
                while (true) {
                    num++;
                }
            }
        });

        EXECUTORS.execute(() -> {
            synchronized (lock) {
                while (true) {
                    num++;
                }
            }
        });
    }


}
