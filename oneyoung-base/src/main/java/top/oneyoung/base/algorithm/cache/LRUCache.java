package top.oneyoung.base.algorithm.cache;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * LRUCache  最近最少使用
 *
 * @author oneyoung
 * @since 2023/8/23 14:35
 */
public class LRUCache<K, V> {

    private final ArrayList<K> cacheKey;

    private final int maxSize;

    private int currentSize;

    private final HashMap<K, V> cacheValue;

    public LRUCache(int maxSize) {
        this.cacheKey = new ArrayList<>();
        this.maxSize = maxSize;
        this.cacheValue = new HashMap<>(maxSize);
    }

    public V get(K key) {
        if (cacheKey.contains(key)) {
            cacheKey.remove(key);
            cacheKey.add(key);
            return cacheValue.get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        if (cacheKey.contains(key)) {
            cacheKey.remove(key);
            cacheKey.add(key);
            cacheValue.put(key, value);
        } else {
            if (currentSize >= maxSize) {
                K firstKey = cacheKey.remove(0);
                cacheValue.remove(firstKey);
                System.out.println("remove key: " + firstKey);
                currentSize--;
            }
            cacheKey.add(key);
            cacheValue.put(key, value);
            currentSize++;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        cache.put("4", "4");
        System.out.println(cache.get("3"));
    }

}
