package top.oneyoung.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMapDemo
 *
 * @author oneyoung
 * @since 2023/7/20 020 15:12
 */

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(1);
        concurrentHashMap.put("hello","world");
        concurrentHashMap.get("hello");
    }

//    final V putVal(K key, V value, boolean onlyIfAbsent) {
//        // 1. key 和 value 不能为空
//        if (key == null || value == null) throw new NullPointerException();
//        // 2. 计算hash值
//        int hash = spread(key.hashCode());
//        int binCount = 0;
//        // 3. for循环，不断的尝试添加元素
//        for (ConcurrentHashMap.Node<K,V>[] tab = table;;) {
//            // 4. f 为计算出来的index位置的node n 为table的长度  i 为计算出来的index fh 为f的hash值
//            ConcurrentHashMap.Node<K,V> f; int n, i, fh;
//            if (tab == null || (n = tab.length) == 0)
//                tab = initTable();
//            // 5. 如果f为空，直接newNode，放到index位置
//            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
//                // 6. cas操作，如果tab[i]为空，就直接放到tab[i]位置 如果失败 说明有其他线程已经放到tab[i]位置了，就继续循环
//                if (casTabAt(tab, i, null,
//                    new ConcurrentHashMap.Node<K,V>(hash, key, value, null)))
//                    //  6.1 退出循环 binCount = 0 说明没有发生冲突 binCount = 1 说明发生了冲突 binCount = 2 说明发生了树化
//                    break;                   // no lock when adding to empty bin
//            }
//            // 7. 如果f不为空，判断f的hash值和key是否和put的相同
//            else if ((fh = f.hash) == MOVED)
//                // 8. 如果f的hash值为MOVED，说明正在扩容，帮助扩容
//                tab = helpTransfer(tab, f);
//            else { // 9. 如果f的hash值不为MOVED，说明没有扩容
//                // 10. 用synchronized加锁，锁住f
//                V oldVal = null;
//                synchronized (f) {
//                    // 11. 判断f是否被修改了，如果没有修改，就可以修改f了
//                    if (tabAt(tab, i) == f) {
//                        // 12. 如果f的hash值小于0，说明f是红黑树 如果f的hash值大于0，说明f是链表
//                        if (fh >= 0) {
//                            // 13. 用for循环，不断的尝试添加元素
//                            binCount = 1;
//                            for (ConcurrentHashMap.Node<K,V> e = f;; ++binCount) {
//                                K ek;
//                                // 14. 如果e的hash值和key和put的相同，就修改e的value值
//                                if (e.hash == hash &&
//                                    ((ek = e.key) == key ||
//                                        (ek != null && key.equals(ek)))) {
//                                    oldVal = e.val;
//                                    if (!onlyIfAbsent)
//                                        e.val = value;
//                                    break;
//                                }
//                                // 15. 如果e的hash值和key和put的不相同，就判断e的next是否为空，如果为空，就直接newNode，放到index位置
//                                ConcurrentHashMap.Node<K,V> pred = e;
//                                if ((e = e.next) == null) {
//                                    pred.next = new ConcurrentHashMap.Node<K,V>(hash, key,
//                                        value, null);
//                                    break;
//                                }
//                                // 16. 如果e的hash值和key和put的不相同，就判断e的next是否为空，如果不为空，就继续循环
//                            }
//                        }
//                        // 17. 如果f的hash值小于0，说明f是红黑树
//                        else if (f instanceof ConcurrentHashMap.TreeBin) {
//                            ConcurrentHashMap.Node<K,V> p;
//                            binCount = 2;
//                            // 18. 调用putTreeVal方法，添加元素
//                            if ((p = ((ConcurrentHashMap.TreeBin<K,V>)f).putTreeVal(hash, key,
//                                value)) != null) {
//                                oldVal = p.val;
//                                if (!onlyIfAbsent)
//                                    p.val = value;
//                            }
//                        }
//                    }
//                }
//                // 19. 如果binCount不为0，说明发生了冲突
//                if (binCount != 0) {
//                    // 20. 如果binCount大于等于8，说明链表长度大于等于8，就把链表转成红黑树
//                    if (binCount >= TREEIFY_THRESHOLD)
//                        // 21. 调用treeifyBin方法，把链表转成红黑树
//                        treeifyBin(tab, i);
//                    // 22. 如果oldVal不为空，说明发生了冲突，就返回oldVal
//                    if (oldVal != null)
//                        return oldVal;
//                    break;
//                }
//            }
//        }
//        // 23. 调用addCount方法，修改map的size
//        addCount(1L, binCount);
//        return null;
//    }
//
//    /**
//     *
//     *
//     * @param x
//     * @param check
//     */
//    private final void addCount(long x, int check) {
//        ConcurrentHashMap.CounterCell[] as; long b, s;
//        if ((as = counterCells) != null ||
//            !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
//            ConcurrentHashMap.CounterCell a; long v; int m;
//            boolean uncontended = true;
//            if (as == null || (m = as.length - 1) < 0 ||
//                (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
//                !(uncontended =
//                    U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
//                fullAddCount(x, uncontended);
//                return;
//            }
//            if (check <= 1)
//                return;
//            s = sumCount();
//        }
//        if (check >= 0) {
//            ConcurrentHashMap.Node<K,V>[] tab, nt; int n, sc;
//            while (s >= (long)(sc = sizeCtl) && (tab = table) != null &&
//                (n = tab.length) < MAXIMUM_CAPACITY) {
//                int rs = resizeStamp(n);
//                if (sc < 0) {
//                    if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
//                        sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
//                        transferIndex <= 0)
//                        break;
//                    if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
//                        transfer(tab, nt);
//                }
//                else if (U.compareAndSwapInt(this, SIZECTL, sc,
//                    (rs << RESIZE_STAMP_SHIFT) + 2))
//                    transfer(tab, null);
//                s = sumCount();
//            }
//        }
//    }
}
