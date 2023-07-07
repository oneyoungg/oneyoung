package oneyoung.leetcode;

/**
 * Solution
 *
 * @author oneyoung
 * @since 2022/2/24 7:31 PM
 */
public class Solution {
    public static void main(String[] args) {
        boolean test = test(1234321);
        System.out.println(test);
    }

    public static boolean test(int num) {
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        int mid = chars.length >> 1;
        String front = str.substring(0, mid);
        String tail = str.substring(mid + 1, chars.length);
        char[] chars1 = tail.toCharArray();
        char[] chars2 = front.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            System.out.println("chars2[i] = " + chars2[i]);
            System.out.println("chars1[chars1.length - i - 1] = " + chars1[chars1.length - i - 1]);
            if (chars2[i] != chars1[chars1.length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
