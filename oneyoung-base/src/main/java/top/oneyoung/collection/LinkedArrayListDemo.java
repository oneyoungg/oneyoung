package top.oneyoung.collection;

import java.util.LinkedList;

/**
 * LinkedArrayListDemo
 *
 * @author oneyoung
 * @since 2023/7/18 10:38
 */
public class LinkedArrayListDemo {
    public static void main(String[] args) {
        // 1. LinkedList的构造方法
        LinkedList<Object> objects = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            // 双端队列 从尾部添加
            objects.add(i);
        }
        Object o = objects.get(20);
        objects.remove(1);
        System.out.println("o = " + o);
    }
}
