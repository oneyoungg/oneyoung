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
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            return pop;
        }
    }

}
