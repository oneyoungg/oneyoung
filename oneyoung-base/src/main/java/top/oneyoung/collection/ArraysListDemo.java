package top.oneyoung.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * ArraysListDemo
 *
 * @author oneyoung
 * @since 2023/7/17 16:54
 */
public class ArraysListDemo {
    // 学习ArrayList的源码
    public static void main(String[] args) {
        // 1. ArrayList的构造方法
        // DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 第一次add时，会初始化DEFAULT_CAPACITY = 10
            objects.add(i);
        }
        //  2. ArrayList的扩容 10 * 1.5 = 15 扩容为1.5倍
        objects.add("hello");
        // 3. ArrayList的遍历
        Object o = objects.get(0);
        objects.remove(0);
        System.out.println("objects = " + objects);
    }

}
