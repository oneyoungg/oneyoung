package top.oneyoung.leetcode;


import top.oneyoung.util.LinkListUtil;
import top.oneyoung.util.ListNode;

/**
 * AddTwoNumbers
 *
 * @author oneyoung
 * @date 2020/12/8 22:25
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = addTwoNumbers(LinkListUtil.newInstance(new int[]{2, 4, 3}),
                LinkListUtil.newInstance(new int[]{5, 6, 4}));
        System.out.println(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tail = new ListNode(0);
        ListNode dy = tail;
        int tmp = 0;
        while (l1 != null || l2 != null) {
            int a = 0;
            int b = 0;
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            dy.next = new ListNode((a + b + tmp) % 10);
            dy = dy.next;
            tmp = (a + b + tmp) / 10;
        }
        if (tmp != 0){
            dy.next = new ListNode(tmp);
        }
        return tail.next;
    }

}
