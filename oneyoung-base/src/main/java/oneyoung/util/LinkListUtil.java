package oneyoung.util;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkListUtil
 *
 * @author oneyoung
 * @date 2020/11/1 001 21:59
 */

public class LinkListUtil {

    public static ListNode newInstance(int[] array) {
        List<ListNode> nodes = new ArrayList<>();
        for (int j : array) {
            ListNode listNode = new ListNode(j);
            nodes.add(listNode);
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (i == nodes.size() - 1) {
                nodes.get(i).next = null;
                continue;
            }
            nodes.get(i).next = nodes.get(i + 1);
        }
        return nodes.get(0);
    }
}
