package top.oneyoung.algorithm.tree;

import top.oneyoung.tree.TreeNode;
import top.oneyoung.tree.TreeUtil;

/**
 * TreeForEarch
 *
 * @author oneyoung
 * @since 2023-08-20 22:01
 */

public class TreeForEarch {

    public static void main(String[] args) {
        TreeNode treeNode = TreeUtil.buildBTree(new Integer[]{1, 7, 9, 3, 2});
        tral(treeNode);
    }

    public static void tral(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        tral(treeNode.left);
        tral(treeNode.right);

    }
}
