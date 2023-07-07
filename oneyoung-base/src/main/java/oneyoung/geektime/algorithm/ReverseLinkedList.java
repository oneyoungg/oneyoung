package oneyoung.geektime.algorithm;

import top.oneyoung.util.LinkListUtil;
import top.oneyoung.util.ListNode;

/**
 * ReverseLinkedList
 *
 * @author oneyoung
 * @date 2020/11/1 001 21:01
 */

public class ReverseLinkedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static void main(String[] args) {
        ListNode listNode = LinkListUtil.newInstance(new int[]{1, 2, 3, 4, 5});
        ListNode result = reverseList(listNode);
        System.out.println(result);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp;
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static ListNode recursion(ListNode listNode) {
        return null;
    }
}
