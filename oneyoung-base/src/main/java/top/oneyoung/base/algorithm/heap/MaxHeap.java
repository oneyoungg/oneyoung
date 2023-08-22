package top.oneyoung.base.algorithm.heap;

/**
 * MaxHeap
 *
 * @author oneyoung
 * @since 2023/8/22 10:43
 */
public class MaxHeap {
    /**
     * 堆数组
     */
    private int[] heap;
    /**
     * 堆的容量
     */
    private int capacity;
    /**
     * 堆中元素的数量
     */
    private int size;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public void insert(int val) {
        if (size == capacity) {
            throw new RuntimeException("Heap is full");
        }

        size++;
        heap[size - 1] = val;
        int currentIdx = size - 1;
        int parentIdx = (currentIdx - 1) / 2;

        while (currentIdx > 0 && heap[currentIdx] > heap[parentIdx]) {
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = (currentIdx - 1) / 2;
        }
    }

    public int removeMax() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);
        return max;
    }

    private void maxHeapify(int idx) {
        int leftChildIdx = 2 * idx + 1;
        int rightChildIdx = 2 * idx + 2;
        int largestIdx = idx;

        if (leftChildIdx < size && heap[leftChildIdx] > heap[largestIdx]) {
            largestIdx = leftChildIdx;
        }

        if (rightChildIdx < size && heap[rightChildIdx] > heap[largestIdx]) {
            largestIdx = rightChildIdx;
        }

        if (largestIdx != idx) {
            swap(idx, largestIdx);
            maxHeapify(largestIdx);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
