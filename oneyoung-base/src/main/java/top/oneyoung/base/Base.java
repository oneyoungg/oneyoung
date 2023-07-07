package top.oneyoung.base;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * main
 *
 * @author oneyoung
 * @since 2023/7/7 14:17
 */
public class Base {

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE =
            new ScheduledThreadPoolExecutor(1,new ThreadPoolExecutor.AbortPolicy());

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(1,
                    3,
                    0,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(1);
        for (int i1 = 0; i1 < 100; i1++) {
            THREAD_POOL_EXECUTOR.execute(() ->{
                System.out.println("hello world" + i.getAndIncrement());
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("THREAD_POOL_EXECUTOR.getActiveCount() = " + THREAD_POOL_EXECUTOR.getActiveCount());
            BlockingQueue<Runnable> queue = THREAD_POOL_EXECUTOR.getQueue();
            System.out.println("queue.size() = " + queue.size());
        }


        Thread.sleep(10000);

    }

}
