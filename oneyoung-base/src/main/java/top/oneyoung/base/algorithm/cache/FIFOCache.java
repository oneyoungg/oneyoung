package top.oneyoung.base.algorithm.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FIFOCache
 *
 * @author oneyoung
 * @since 2023/8/23 14:05
 */
public class FIFOCache<K, V> {

    /**
     * 缓存key
     */
    private final LinkedList<K> cacheKey;
    /**
     * 缓存value
     */
    private final HashMap<K, V> cacheValue;
    /**
     * 缓存锁 保证线程安全
     */
    private final Lock lock = new ReentrantLock();
    /**
     * 缓存最大容量
     */
    private int maxSize;
    /**
     * 当前缓存大小
     */
    private int currentSize;

    public FIFOCache(int maxSize) {
        this.cacheKey = new LinkedList<>();
        this.cacheValue = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    public V get(K key) {
        lock.lock();
        try {
            return cacheValue.get(key);
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (currentSize >= maxSize) {
                K firstKey = cacheKey.removeFirst();
                cacheValue.remove(firstKey);
                currentSize--;
            }
            cacheKey.addLast(key);
            cacheValue.put(key, value);
            currentSize++;
        } finally {
            lock.unlock();
        }
    }

    public void remove(K key) {
        lock.lock();
        try {
            cacheKey.remove(key);
            cacheValue.remove(key);
            currentSize--;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            cacheKey.clear();
            cacheValue.clear();
            currentSize = 0;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return currentSize;
    }

    public int maxSize() {
        return maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean containsKey(K key) {
        return cacheValue.containsKey(key);
    }

    public boolean containsValue(V value) {
        return cacheValue.containsValue(value);
    }

    public static void main(String[] args) {
        FIFOCache<String, String> cache = new FIFOCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache.get("1"));
        cache.put("4", "4");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        System.out.println(cache.get("3"));
        System.out.println(cache.get("4"));
    }
}
