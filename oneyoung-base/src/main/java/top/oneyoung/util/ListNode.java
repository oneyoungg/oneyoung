package top.oneyoung.util;

/**
 * ListNode
 *
 * @author oneyoung
 * @date 2020/11/1 001 21:02
 */

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

     public int getVal() {
          return val;
     }

     public void setVal(int val) {
          this.val = val;
     }

     public ListNode getNext() {
          return next;
     }

     public void setNext(ListNode next) {
          this.next = next;
     }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
