package top.oneyoung.base.concurrent.futrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * FutrueTest
 *
 * @author oneyoung
 * @since 2023/8/25 14:33
 */
public class FutrueTest {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> hello = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("hello");
            return 1;
        });
        while (!hello.isDone()) {
            Thread.sleep(100L);
            System.out.println("wait");
        }
        System.out.println(hello.get());

    }
}
