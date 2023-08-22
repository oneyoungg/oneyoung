package top.oneyoung.base.algorithm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapSort3
 *
 * @author oneyoung
 * @since 2023/8/22 09:55
 */
public class HeapSort3 {
    public static void main(String[] args) {
        List<QueueTask> maxPriorityQueue = new ArrayList<>();
        maxPriorityQueue.add(new QueueTask(3, "task1"));
        maxPriorityQueue.add(new QueueTask(5, "task2"));
        maxPriorityQueue.add(new QueueTask(10, "task3"));
        maxPriorityQueue.add(new QueueTask(2, "task4"));
        maxPriorityQueue.add(new QueueTask(7, "task5"));
        maxPriorityQueue.add(new QueueTask(9, "task6"));
        maxPriorityQueue.add(new QueueTask(1, "task7"));
        maxPriorityQueue.add(new QueueTask(4, "task8"));
        maxPriorityQueue.add(new QueueTask(6, "task9"));
        maxPriorityQueue.add(new QueueTask(8, "task10"));
        QueueTask[] array = maxPriorityQueue.toArray(new QueueTask[]{});
        sort(array);
        for (QueueTask queueTask : array) {
            queueTask.execute();
        }

        sort(new Integer[]{1,3,2});
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, length);
        }
        for (int i = length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] arr, int top, int length) {
        int currentTop = top;
        int left = 2 * top + 1;
        int right = 2 * top + 2;

        if (left < length && arr[left].compareTo(arr[currentTop]) > 0) {
            currentTop = left;
        }
        if (right < length && arr[right].compareTo(arr[currentTop]) > 0) {
            currentTop = right;
        }

        if (currentTop != top) {
            swap(arr, currentTop, top);
            heapify(arr, currentTop, length);
        }

    }

    private static <T extends Comparable<T>> void swap(T[] arr, int currentTop, int top) {
        T tmp = arr[currentTop];
        arr[currentTop] = arr[top];
        arr[top] = tmp;
    }
}
