package top.oneyoung.base.algorithm.heap;

import java.util.Arrays;

/**
 * TopK
 *
 * @author oneyoung
 * @since 2023/8/22 10:47
 */
public class TopK {

    public static void main(String[] args) {
        long[] longs = topK(new long[]{10, 5, 2, 1, 35, 13, 73, 12, 100, 30, 3, 4}, 5);
        System.out.println(Arrays.toString(longs));
    }

    public static long[] topK(long[] arr, int k) {
        long[] num = new long[k];
        int currentIndex = -1;
        for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
            long l = arr[i];
            if (i < k) {
                // 插入 建堆
                num[++currentIndex] = l;
                int parent = (currentIndex - 1) >>> 1;
                while (currentIndex > 0 && num[currentIndex] > num[parent]) {
                    swap(num, currentIndex, parent);
                    currentIndex = parent;
                    parent = (currentIndex - 1) >>> 1;
                }
            } else {
                if (l < num[0]) {
                    num[0] = l;
                    heapify(num, 0, num.length);
                }
            }
        }
        return num;
    }

    private static void heapify(long[] num, int top, int length) {
        int currentTop = top;
        int left = (currentTop << 1) + 1;
        int right = (currentTop << 1) + 2;

        if (left < length && num[left] > num[top]) {
            currentTop = left;
        }

        if (right < left && num[right] > num[top]) {
            currentTop = right;
        }

        if (currentTop != top) {
            swap(num, currentTop, top);
            heapify(num, currentTop, length);
        }
    }

    private static void swap(long[] heap, int i, int j) {
        long temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
