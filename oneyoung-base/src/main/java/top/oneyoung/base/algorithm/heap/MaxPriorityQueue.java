package top.oneyoung.base.algorithm.heap;

/**
 * MaxPriorityQueue
 *
 * @author oneyoung
 * @since 2023/8/21 17:39
 */
public class MaxPriorityQueue<T extends Comparable<T> > {

    /**
     * 从下标1开始存储数据
     */
    private T[] items;

    /**
     * 堆可以存储的最大数据个数
     */
    private int capacity;


    /**
     * 堆中已经存储的数据个数
     */
    private int count;

    public MaxPriorityQueue(int capacity) {
        // 从下标1开始存储数据
        this.items = (T[]) new Comparable[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public void add(T value) {
        insert(value);
    }

    public T poll() {
        return removeMax();
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public void insert(T value) {
        if (isFull()) {
            return;
        }
        items[++count] = value;
        int parent = count / 2;
        int current = count;
        while (parent > 0 && items[parent].compareTo(items[current]) < 0) {
            swap(items, parent, current);
            current = parent;
            parent = current / 2;
        }
    }

    public T removeMax() {
        if (isEmpty()) {
            return null;
        }
        T max = items[1];
        items[1] = items[count];
        items[count] = null;
        count--;
        heapify(items, count, 1);
        return max;
    }


    private void heapify(T[] items, int count, int i) {
        int l = i * 2;
        int r = i * 2 + 1;
        while (l <= count && r <= count && (items[i].compareTo(items[l]) < 0 || items[i].compareTo(items[r]) < 0)) {
            if (items[l].compareTo(items[r]) > 0) {
                swap(items, i, l);
                i = l;
            } else {
                swap(items, i, r);
                i = r;
            }
            l = i * 2;
            r = i * 2 + 1;
        }
    }

    private void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }


}
