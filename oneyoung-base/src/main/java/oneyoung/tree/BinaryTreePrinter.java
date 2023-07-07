package oneyoung.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreePrinter {

    // 打印二叉树的方法
    public static void print(Node root) {
        int maxLevel = maxLevel(root); // 获取二叉树的最大深度
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    // 获取二叉树的最大深度
    private static int maxLevel(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    // 打印二叉树节点的方法
    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level; // 计算当前节点所在的层数
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0))); // 计算当前层的边界线数量
        int firstSpaces = (int) Math.pow(2, (floor)) - 1; // 计算当前层第一个节点前面的空格数量
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1; // 计算当前层相邻节点之间的空格数量

        printWhitespaces(firstSpaces); // 打印第一个节点前面的空格

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (node.left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(i + i - 1);

                if (node.right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    // 判断节点列表中是否全为null
    private static boolean isAllElementsNull(List<Node> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    // 打印指定数量的空格
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    // 测试代码
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        print(root);
    }

    // 定义二叉树节点类
    private static class Node {
        Node left;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
        }
    }
}
