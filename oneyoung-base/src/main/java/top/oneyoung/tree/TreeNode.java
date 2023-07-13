package top.oneyoung.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * TreeNode
 *
 * @author oneyoung
 * @since 2023/5/30 18:44
 */
public class TreeNode implements Iterable<TreeNode>{

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new Dep(this);
    }

    private static class Dep implements Iterator<TreeNode> {

        private final Stack<TreeNode> stack = new Stack<>();

        public Dep(TreeNode current) {
            stack.push(current);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode pop = stack.pop();
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
            return pop;
        }
    }

    public void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val); // 访问节点
            if (node.right != null) {
                stack.push(node.right); // 先将右子节点入栈
            }
            if (node.left != null) {
                stack.push(node.left); // 后将左子节点入栈
            }
        }
    }

    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                // 将当前节点入栈
                stack.push(node);
                // 一直往左子树走
                node = node.left;
            }
            node = stack.pop();
            // 访问节点
            System.out.println(node.val);
            // 如果有右子树，继续遍历右子树
            node = node.right;
        }
    }

    public static void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        // 用于记录上一次访问的节点
        TreeNode prev = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == prev) {
                stack.pop();
                // 访问节点
                System.out.println(node.val);
                prev = node;
                node = null;
            } else {
                // 如果有右子树，继续遍历右子树
                node = node.right;
            }
        }
    }

}
