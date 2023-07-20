package top.oneyoung.util;

/**
 * RedBlackTree
 *
 * @author oneyoung
 * @since 2023/7/20 020 12:34
 */

public class RedBlackTree {
    private Node root;

    // 左旋操作
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // 右旋操作
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // 插入操作
    public void insert(int value) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.color = 1;  // 新插入的节点为红色
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        fixInsert(newNode);
    }

    // 插入后修正红黑树
    private void fixInsert(Node node) {
        while (node.parent != null && node.parent.color == 1) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle != null && uncle.color == 1) {
                    node.parent.color = 0;
                    uncle.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle != null && uncle.color == 1) {
                    node.parent.color = 0;
                    uncle.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = 0;
    }
}

class Node {
    int value;
    Node parent;
    Node left;
    Node right;
    int color;  // 0表示黑色，1表示红色
}
