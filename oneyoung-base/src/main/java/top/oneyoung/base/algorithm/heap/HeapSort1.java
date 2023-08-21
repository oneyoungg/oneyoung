package top.oneyoung.base.algorithm.heap;

import java.util.Arrays;

/**
 * HeapSort1
 *
 * @author oneyoung
 * @since 2023-08-21 22:35
 */

public class HeapSort1 {

    /**
     * 堆排序入口方法
     *
     * @param num 待排序数组
     */
    public static void heapSort(int[] num) {
        int length = num.length;

        // 构建大顶堆 堆的性质是父节点的值大于等于子节点的值，所以我们需要从最后一个非叶子节点开始向上调整堆的结构，以保证整个堆是一个大顶堆。
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(num, length, i);
        }

        // 依次将堆顶元素与末尾元素交换，并重新调整堆
        for (int i = length - 1; i >= 0; i--) {
            // 将堆顶元素与末尾元素交换
            swap(num, 0, i);

            // 调整堆
            heapify(num, i, 0);
        }
    }

    /**
     * 构建大顶堆
     *
     * @param arr 待排序数组
     * @param length   数组长度
     * @param top   当前节点的索引
     */
    private static void heapify(int[] arr, int length, int top) {
        int currentTop = top; // 将当前节点设置为最大值节点
        int left = 2 * top + 1; // 左子节点的索引
        int right = 2 * top + 2; // 右子节点的索引

        // 如果左子节点大于最大值节点，则更新最大值节点
        if (left < length && arr[left] > arr[currentTop]) {
            currentTop = left;
        }

        // 如果右子节点大于最大值节点，则更新最大值节点
        if (right < length && arr[right] > arr[currentTop]) {
            currentTop = right;
        }

        // 如果最大值节点不是当前节点，则交换它们，并递归调整堆
        if (currentTop != top) {
            swap(arr, top, currentTop);
            heapify(arr, length, currentTop);
        }
    }

    private static void swap(int[] arr, int i, int largest) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
