package top.oneyoung.tree;

/**
 * TreeUtil
 *
 * @author oneyoung
 * @since 2023/5/30 18:45
 */
public class TreeUtil {
    public static void main(String[] args) {
        TreeUtil treeUtil = new TreeUtil();
        Integer[] nums = {1, 9, 3, 4, 5, 10, 7, 8, 20};
        TreeNode treeNode = treeUtil.buildBTree(nums);
//        dep(treeNode);
        TreeNode.postorderTraversal(treeNode);
//        for (TreeNode node : treeNode) {
//            System.out.print(node.val + " ");
//        }
    }
    /**
     * 根据数组 建立二叉查找树
     *
     * @param nums 数据
     * @return 根节点
     */
    public static TreeNode buildBTree(Integer[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == null) {
                continue;
            }
            insert(root, nums[i]);
        }
        return root;
    }

    /**
     * 插入节点
     *
     * @param root 根节点
     * @param val  节点值
     */
    private static void insert(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insert(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insert(root.right, val);
            }
        }
    }

    private static void dep(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        dep(root.left);
        dep(root.right);
    }


}
