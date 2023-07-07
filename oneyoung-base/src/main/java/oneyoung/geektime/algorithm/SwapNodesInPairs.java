package oneyoung.geektime.algorithm;

import top.oneyoung.util.LinkListUtil;
import top.oneyoung.util.ListNode;

/**
 * SwapNodesInPairs
 *
 * @author oneyoung
 * @date 2020/11/2 1:28
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode node = LinkListUtil.newInstance(new int[]{1, 2, 3, 4, 5});
        System.out.println(solution(node));

    }

    public static ListNode solution(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = node1.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
