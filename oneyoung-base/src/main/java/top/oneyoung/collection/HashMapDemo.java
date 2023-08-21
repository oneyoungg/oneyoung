//package top.oneyoung.collection;
//
//import java.util.HashMap;
//
///**
// * HashMapDemo
// *
// * @author oneyoung
// * @since 2023/7/18 11:37
// */
//public class HashMapDemo {
//
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//    public static void main(String[] args) {
//        HashMap<Object, Object> map = new HashMap<>(10);
//        map.put("hello","world");
//        map.putIfAbsent("hello","world1111");
//        System.out.println(map.get("hello1"));
//        tableSizeFor(10);
//    }
//
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        printBinary(n);
//        n |= n >>> 1;
//        printBinary(n);
//        n |= n >>> 2;
//        printBinary(n);
//        n |= n >>> 4;
//        printBinary(n);
//        n |= n >>> 8;
//        printBinary(n);
//        n |= n >>> 16;
//        printBinary(n);
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    public static void  printBinary(int num){
//        System.out.println(Integer.toBinaryString(num));
//    }
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
//}
