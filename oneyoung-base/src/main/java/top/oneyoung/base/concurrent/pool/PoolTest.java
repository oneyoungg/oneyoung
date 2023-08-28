package top.oneyoung.base.concurrent.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PoolTest
 *
 * @author oneyoung
 * @since 2023/8/25 17:34
 */
public class PoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                2,
                0L,
                TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(10)
                , new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.submit(() -> {
            System.out.println("hello1");
        });

        threadPoolExecutor.submit(() -> {
            System.out.println("hello2");
        });

        System.out.println(threadPoolExecutor.getPoolSize());
    }
}
