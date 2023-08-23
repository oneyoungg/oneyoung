package top.oneyoung.base.algorithm.cache;

import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LFUCache 最近最不常用
 *
 * @author oneyoung
 * @since 2023/8/23 15:03
 */
public class LFUCache<K, V> {
    private final int maxSize;

    private final HashMap<K, V> cache;

    private final LinkedList<Node<K>> cacheKey;

    private final Map<K, Node<K>> cacheKeyMap;

    private int currentSize;


    public LFUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>(maxSize);
        this.cacheKey = new LinkedList<>();
        this.cacheKeyMap = new HashMap<>(maxSize);
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node<K> node = cacheKeyMap.get(key);
            node.count++;
            // 重新排序
            addAndSort(cacheKey, node);
            return cache.get(key);
        }
        return null;
    }

    public void addAndSort(LinkedList<Node<K>> linkedList, Node<K> node) {
        // 先删除 重新排序
        linkedList.remove(node);
        int oldSize = linkedList.size();
        // 从头开始遍历，如果比当前的小，就插入到前面
        for (int i = 0, linkedListSize = linkedList.size(); i < linkedListSize; i++) {
            Node<K> kNode = linkedList.get(i);
            if (kNode.count > node.count) {
                // 如果是最后一个，直接插入到最后
                if (i == oldSize - 1) {
                    linkedList.addLast(node);
                    break;
                }
                if (linkedList.get(i + 1).count != kNode.count) {
                    linkedList.add(i, node);
                    break;
                }
            }
        }
        if (oldSize == linkedList.size()) {
            linkedList.addFirst(node);
        }
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K> node = cacheKeyMap.get(key);
            node.count++;
            addAndSort(cacheKey, node);
            cache.put(key, value);
        } else {
            if (currentSize >= maxSize) {
                Node<K> removeLast = cacheKey.removeLast();
                cacheKeyMap.remove(removeLast.key);
                cache.remove(removeLast.key);
                currentSize--;
            }
            Node<K> node = new Node<>(key, 1);
            addAndSort(cacheKey, node);
            cacheKeyMap.put(key, node);
            cache.put(key, value);
            currentSize++;

        }
    }

    public void print() {
        System.out.println(cacheKey);
    }


    @ToString
    public static class Node<T> implements Comparable<Node<T>> {

        private final T key;

        private int count;

        public Node(T key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.count - o.count;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                return this.key.equals(((Node) obj).key);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    public static void main(String[] args) {
        LFUCache<String, String> cache = new LFUCache<>(3);
        cache.put("1", "1");
        cache.print();
        cache.put("2", "2");
        cache.print();
        cache.put("3", "3");
        cache.print();
        System.out.println(cache.get("1"));
        cache.print();
        System.out.println(cache.get("2"));
        cache.print();
        cache.put("4", "4");
        cache.print();
        System.out.println(cache.get("3"));
        cache.print();
        System.out.println(cache.get("4"));
        cache.print();

    }
}
