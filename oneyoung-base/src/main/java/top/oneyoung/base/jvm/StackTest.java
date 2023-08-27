package top.oneyoung.base.jvm;

import java.util.concurrent.TimeUnit;

/**
 * StackTest
 *
 * @author oneyoung
 * @since 2023-08-26 18:28
 */

public class StackTest {

    public static void main(String[] args) throws Exception{
        StackTest stackTest = new StackTest();
//        stackTest.recursion(1, 2, 3);
        stackTest.test();
        TimeUnit.SECONDS.sleep(100000000);
    }

    public void recursion(long a, long b, long c) {
        long d = 0, e = 0, f = 0;
        recursion(a, b, c);
    }

    public void test() {
        System.out.println("test");
        test2();
    }
    public void test2() {
        System.out.println("test2");
        test3();
    }

    public void test3() {
        System.out.println("test3");
        test4();
    }
    public void test4() {
        System.out.println("test4");

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement);
        }
        System.out.println("test4");
    }

}
