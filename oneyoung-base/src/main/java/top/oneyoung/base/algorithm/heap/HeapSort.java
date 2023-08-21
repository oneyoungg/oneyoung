package top.oneyoung.base.algorithm.heap;


import java.util.Arrays;

/**
 * Heap
 *
 * @author oneyoung
 * @since 2023-08-21 21:17
 */
@SuppressWarnings("ALL")
public class HeapSort {

    public static void main(String[] args) {
        int[] num = {30, 123, 13345, 23476, 45, 12334, 6, 82, 18, 5, 2};
        System.out.println(Arrays.toString(num));
        sort(num);
        System.out.println(Arrays.toString(num));
    }

    public static void sort(int[] source) {
        // 构建堆
        int[] heap = new int[source.length + 1];
        System.arraycopy(source, 0, heap, 1, source.length);
        for (int i = heap.length - 1; i > 1; i--) {
            int parent = i / 2;
            if (heap[i] > heap[parent]) {
                swap(heap, i, parent);
                heapfy(heap, i, heap.length);
            }
            if (heap[i - 1] > heap[parent]) {
                swap(heap, i - 1, parent);
                heapfy(heap, i - 1, heap.length);
            }

        }
        System.out.println("heap : " + Arrays.toString(heap));
        // 堆排序
        for (int i = heap.length - 1; i >= 1; i--) {
            swap(heap, 1, i);
            heapfy(heap, 1, i - 1);
        }
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    public static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public static void heapfy(int[] num, int top, int length) {
        int l = top * 2;
        int r = top * 2 + 1;
        while (l < length && r < length && (num[l] > num[top] || num[r] > num[top])) {
            if (num[r] > num[l]) {
                swap(num, r, top);
                top = r;
            } else {
                swap(num, l, top);
                top = l;
            }
            l = top * 2;
            r = top * 2 + 1;
        }
    }
}
