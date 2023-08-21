package top.oneyoung.base.algorithm.heap;

import java.util.Arrays;
import java.util.Random;

/**
 * HeapSort2
 *
 * @author oneyoung
 * @since 2023-08-21 22:52
 */

public class HeapSort2 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] num = new int[20];
        for (int i = num.length - 1; i >= 0; i--) {
            num[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(num));
        sort(num);
        System.out.println(Arrays.toString(num));
    }

    public static void sort(int[] num) {
        int length = num.length;
        // 堆化
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(num, i, length);
        }
        // 排序
        for (int i = length - 1; i >= 0; i--) {
            swap(num, 0, i);
            heapify(num, 0, i);
        }
    }

    private static void heapify(int[] num, int top, int length) {
        int currentTop = top;
        int left = currentTop * 2 + 1;
        int right = currentTop * 2 + 2;
        if (left < length && num[left] > num[currentTop]) {
            currentTop = left;
        }
        if (right < length && num[right] > num[currentTop]) {
            currentTop = right;
        }

        if (currentTop != top) {
            swap(num, currentTop, top);
            heapify(num, currentTop, length);
        }
    }


    public static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;

    }
}
