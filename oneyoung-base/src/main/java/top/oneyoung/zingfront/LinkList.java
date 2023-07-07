package top.oneyoung.zingfront;

import java.util.LinkedList;

/**
 * 新建链表 将第一条链表头尾插入新链表，然后移除 直到第一条链表为空结束
 *
 * @author oneyoung
 * @date 2020/10/13 013 22:42
 */

public class LinkList {

    public static void main(String[] args) {
        LinkedList<Integer> ints = new LinkedList<>();
        ints.addLast(1);ints.addLast(2);ints.addLast(3);
        ints.addLast(4);ints.addLast(5);ints.addLast(6);
        LinkedList<Integer> ints2 = new LinkedList<>();
        while (!ints.isEmpty()){
            ints2.addLast(ints.getFirst());
            ints2.addLast(ints.getLast());
            ints.pollFirst();
            ints.pollLast();
        }
        System.out.println(ints2);
    }
}
