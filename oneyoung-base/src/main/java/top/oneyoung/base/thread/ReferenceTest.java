package top.oneyoung.base.thread;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ReferenceTest
 *
 * @author oneyoung
 * @since 2023-08-24 22:53
 */

public class ReferenceTest {

    public static void main(String[] args) {
//        testReference();
//        testSoftReference();
//        testWeakReference();
        testPhantomReference();
    }

    /**
     * 强引用 测试
     * 1. 强引用不会被gc回收
     * 2. 强引用指向的对象被置为null后，gc才会回收
     * 3. 强引用指向的对象被置为null后，gc不会立即回收，而是等待下一次gc，但是如果内存不足，gc会立即回收
     */
    public static void testReference() {
        User user = new User();
        System.out.println("gc before " + user);
        user = null;
        System.gc();
        System.out.println("gc after  " + user);
    }

    /**
     * -Xmx20m -XX:+PrintGCDetails -verbose:gc -XX:+PrintGCTimeStamps
     * 只有在内存不足的时候才会回收软引用
     */
    public static void testSoftReference() {
        SoftReference<User> userSoftReference = new SoftReference<>(new User());
        System.out.println("gc before " + userSoftReference.get());
        try {
            TimeUnit.SECONDS.sleep(1);
//            cosummerMemory(99);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after  " + userSoftReference.get());
        }
    }

    /**
     * 只要gc就会回收弱引用
     */
    public static void testWeakReference() {
        WeakReference<User> userWeakReference = new WeakReference<>(new User());
        System.out.println("gc before " + userWeakReference.get());
        try {
            TimeUnit.SECONDS.sleep(1);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after  " + userWeakReference.get());
        }
    }

    /**
     *  虚引用 一般用于管理堆外内存 本地内存 本地内存不受gc管理 但是可以通过虚引用来管理 一旦gc就会回收
     *  1. 虚引用必须和引用队列一起使用
     *  2. 虚引用的get方法永远返回null
     *  3. 虚引用的构造方法必须传入引用队列
     *  4.任何时候都可能被gc回收
     */
    public static void testPhantomReference() {
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<User> userWeakReference = new WeakReference<>(new User(), referenceQueue);
        System.out.println("gc before " + userWeakReference.get());
        System.gc();
        cosummerMemory(99);
    }

    private static void cosummerMemory(int total) {
        List<byte[]> list = new java.util.ArrayList<>();
        for (int i = 0; i < total; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }
}
