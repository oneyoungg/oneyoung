package top.oneyoung.algorithm.search;

/**
 * BinarySearch
 *
 * @author oneyoung
 * @since 2023-08-20 21:20
 */

public class BinarySearch {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
//        int search = search(ints, 3);
//        System.out.println(search);
        System.out.println(binarySearch0(ints, 0, ints.length, 5));
    }

    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static int search(int[] num, int target) {
        if (num == null || num.length == 0) {
            return -1;
        }
        int i = num.length / 2;
        if (num[i] == target) {
            return i;
        } else if (num[i] < target) {
            return search(newArray(num, i + 1, num.length - 1), target);
        } else if (num[i] > target) {
            return search(newArray(num, 0, i - 1), target);
        }
        return -1;
    }

    public static int[] newArray(int[] num, int start, int end) {
        int length = end - start + 1;
        int[] ints = new int[length];
        for (int i = 0; i < ints.length && end <= start; i++) {
            ints[i] = num[start++];
        }
        return ints;
    }
}
