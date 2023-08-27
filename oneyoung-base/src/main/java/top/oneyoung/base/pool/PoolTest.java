package top.oneyoung.base.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PoolTest
 *
 * @author oneyoung
 * @since 2023-08-25 22:16
 */

public class PoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            6,
            6,
            1L, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
        });
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }


    }
}
