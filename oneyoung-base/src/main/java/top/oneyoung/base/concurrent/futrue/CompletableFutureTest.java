package top.oneyoung.base.concurrent.futrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureTest
 *
 * @author oneyoung
 * @since 2023/8/25 14:40
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
//        combine();
//        allOf();
        anyOf();
        TimeUnit.SECONDS.sleep(6);
    }

    public static void anyOf() {
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 1");
            return 1;
        });
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 2");
            return 2;
        });
        CompletableFuture<String> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 3");
            return "3";
        });
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(integerCompletableFuture, integerCompletableFuture1, integerCompletableFuture2);
        objectCompletableFuture.thenAccept(integer -> {
            System.out.println(Thread.currentThread().getName() + " 4");
            System.out.println(integer);
        }); }

    public static void allOf(){
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 1");
            return 1;
        });
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 2");
            return 2;
        });
        CompletableFuture<String> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 3");
            return "3";
        });
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(integerCompletableFuture, integerCompletableFuture1, integerCompletableFuture2);
        voidCompletableFuture.thenAccept(integer -> {
            System.out.println(Thread.currentThread().getName() + " 4");
            System.out.println(integer);
        });
        voidCompletableFuture.join();
    }

    public static void combine(){
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 1");
            return 1;
        });
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 2");
            return 2;
        });
        integerCompletableFuture.thenCombine(integerCompletableFuture1, (integer, integer2) -> {
            System.out.println(Thread.currentThread().getName() + " 3");
            return integer + integer2;
        }).thenAccept(integer -> {
            System.out.println(Thread.currentThread().getName() + " 4");
            System.out.println(integer);
        });
    }

    public static void test1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 1;
        }, executorService);
        integerCompletableFuture.thenApplyAsync(integer -> {
            System.out.println(Thread.currentThread().getName());
            return integer + 1;
        }, executorService).thenAcceptAsync(integer -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(integer);
        }, executorService);
        TimeUnit.SECONDS.sleep(5);
    }
}
