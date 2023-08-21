package top.oneyoung.algorithm.bitmap;

import java.util.BitSet;
import java.util.Random;

/**
 * BitmapTest
 *
 * @author oneyoung
 * @since 2023-08-20 15:21
 */

public class BitmapTest {

    public static void main(String[] args) {
        int[] num = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            num[i] = random.nextInt(101);
        }
        BitSet bitSet = new BitSet(100);
        for (int j : num) {
            bitSet.set(j);
        }
        System.out.println("bitSet.size() = " + bitSet.size());
        System.out.println("bitSet.length() = " + bitSet.length());
        System.out.println("bitSet.cardinality() = " + bitSet.cardinality());
        System.out.println("bitSet = " + bitSet);
    }
}
