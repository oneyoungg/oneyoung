package top.oneyoung.base.algorithm.heap;


/**
 * MinHeap
 *
 * @author oneyoung
 * @since 2023/8/21 16:13
 */
public class MinHeap {

    /**
     * 从下标1开始存储数据
     */
    private final int[] data;
    /**
     * 堆可以存储的最大数据个数
     */
    private final int capacity;
    /**
     * 堆中已经存储的数据个数
     */
    private int count;

    public MinHeap(int capacity) {
        // 从下标1开始存储数据
        this.data = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(10);
        minHeap.insert(2);
        minHeap.insert(7);
        minHeap.insert(9);
        minHeap.insert(1);
        minHeap.insert(4);
        minHeap.insert(6);
        minHeap.insert(8);
        minHeap.printAll();
        minHeap.removeMin();
        minHeap.printAll();
    }

    /**
     * 插入数据
     *
     * @param value 插入的数据
     */
    public void insert(int value) {
        if (count >= capacity) {
            return;
        }
        data[++count] = value;
        int parent = count / 2;
        int current = count;
        while (parent > 0 && data[current] < data[parent]) {
            swap(data, current, parent);
            current = parent;
            parent = current / 2;
        }
    }

    private void swap(int[] data, int current, int parent) {
        int temp = data[current];
        data[current] = data[parent];
        data[parent] = temp;
    }

    /**
     * 删除堆顶元素
     */
    public void removeMin() {
        if (count == 0) {
            return;
        }
        data[1] = data[count--];
        heapify(data, count, 1);
    }

    /**
     * 堆化
     *
     * @param data 堆数据
     * @param count 堆中数据个数
     * @param i 堆化的起始位置
     */
    private void heapify(int[] data, int count, int i) {
        int l = i * 2;
        int r = i * 2 + 1;
        while (l <= count && r <= count && (data[i] > data[l] || data[i] > data[r])) {
            if (data[l] < data[r]) {
                swap(data, i, l);
                i = l;
            } else {
                swap(data, i, r);
                i = r;
            }
            l = i * 2;
            r = i * 2 + 1;
        }
    }

    public void printAll() {
        for (int i = 1; i <= count; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

}
