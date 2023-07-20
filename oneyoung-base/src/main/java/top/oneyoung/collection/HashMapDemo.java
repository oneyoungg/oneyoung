package top.oneyoung.collection;

import java.util.HashMap;

/**
 * HashMapDemo
 *
 * @author oneyoung
 * @since 2023/7/18 11:37
 */
public class HashMapDemo {

    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(10);
        for (int i = 0; i < 16; i++) {
            map.put(i,i);
        }

        map.put("hello","world");
        map.putIfAbsent("hello","world1111");
        map.remove("hello");
        System.out.println(map.get("hello1"));
        tableSizeFor(10);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        printBinary(n);
        n |= n >>> 1;
        printBinary(n);
        n |= n >>> 2;
        printBinary(n);
        n |= n >>> 4;
        printBinary(n);
        n |= n >>> 8;
        printBinary(n);
        n |= n >>> 16;
        printBinary(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void  printBinary(int num){
        System.out.println(Integer.toBinaryString(num));
    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        //  1. table 为空或者长度为0，调用resize()方法，初始化table
//        // n  为table的长度  i 为计算出来的index tab 为table
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        // 2. 计算hash值，计算index = (n - 1) & hash
//        // p 为计算出来的index位置的node
//        // 如果p为空，直接newNode，放到index位置
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        // 3. 如果p不为空，判断p的hash(就是p对应key的hash)值和key是否和put的相同
//        else {
//            // e 为p的下一个节点 k 为p的key
//            Node<K,V> e; K k;
//            // 如果p的hash值和key和put的相同，直接赋值给e
//            if (p.hash == hash &&
//                ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            // 如果p是TreeNode类型，调用putTreeVal方法 二叉树的插入 红黑树 1.8之后 1.8之前是链表 1.8之后是红黑树
//            else if (p instanceof TreeNode)
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            //  如果p不是TreeNode类型，说明是链表，遍历链表，如果key相同，直接赋值给e
//            else {
//                for (int binCount = 0; ; ++binCount) {
//                    // 如果p的下一个节点为空，说明遍历到了链表的最后一个节点，直接newNode，放到链表的最后
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        // 如果链表的长度大于等于8，调用treeifyBin方法，将链表转换成红黑树
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        // 跳出循环
//                        break;
//                    }
//                    // 如果p的下一个节点不为空，判断p的下一个节点的hash值和key是否和put的相同
//                    if (e.hash == hash &&
//                        ((k = e.key) == key || (key != null && key.equals(k))))
//                        // 如果相同，跳出循环
//                        break;
//                    // 如果不相同，p指向p的下一个节点，继续循环
//                    p = e;
//                }
//            }
//            // 如果e不为空，说明key相同，直接赋值给e
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        // 4. 如果p为空，说明是新插入的节点，size++，modCount++，判断size是否大于threshold，如果大于，调用resize()方法，扩容
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//
//    final HashMap.Node<K,V>[] resize() {
//        // oldTab 为旧的table oldCap 为旧的table的长度 oldThr 为旧的threshold
//        HashMap.Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        // newCap 为新的table的长度 newThr 为新的threshold
//        int newCap, newThr = 0;
//        // 如果旧的table的长度大于0
//        if (oldCap > 0) {
//            // 如果旧的table的长度大于等于最大值，直接返回旧的table
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            // 如果旧的table的长度小于最大值，newCap = oldCap << 1，newThr = oldThr << 1
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold
//        }
//        // 如果旧的threshold大于0，newCap = oldThr
//        else if (oldThr > 0) // initial capacity was placed in threshold
//            newCap = oldThr;
//        // 如果旧的threshold等于0，newCap = DEFAULT_INITIAL_CAPACITY，newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY)
//        else {               // zero initial threshold signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        // 如果newThr等于0，newThr = newCap * loadFactor
//        if (newThr == 0) {
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
//                (int)ft : Integer.MAX_VALUE);
//        }
//        // 将新的threshold赋值给threshold
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes","unchecked"})
//        // newTab 为新的table newCap 为新的table的长度
//        HashMap.Node<K,V>[] newTab = (HashMap.Node<K,V>[])new HashMap.Node[newCap];
//        table = newTab;
//        // 如果旧的table不为空，遍历旧的table
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                HashMap.Node<K,V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof HashMap.TreeNode)
//                        ((HashMap.TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//                    else { // preserve order
//                        HashMap.Node<K,V> loHead = null, loTail = null;
//                        HashMap.Node<K,V> hiHead = null, hiTail = null;
//                        HashMap.Node<K,V> next;
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            }
//                            else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }



}
