package top.oneyoung.leetcode;

import java.util.HashSet;

/**
 * MaxSubDistictCharString
 *
 * @author oneyoung
 * @since 2022/2/3 4:32PM
 */
public class MaxSubDistictCharString {

    public static void main(String[] args) {
//        int num = lengthOfLongestSubstring("aab");
        int fast = lengthOfLongestSubstring1("aab");
//        System.out.println(num);
        System.out.println("fast = " + fast);
    }

    /**
     * 暴力
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int ret = 0;
        for (int slow = 0; slow < chars.length; slow++) {
            for (int fast = slow; fast < chars.length; fast++) {
                String substring = s.substring(slow, fast);
                if (!substring.contains(chars[fast] + "")) {
                    ret = Math.max(fast - slow + 1, ret);
                } else {
                    break;
                }
            }
        }
        if (ret == 0 && s.length() > 0) {
            return s.length();
        }
        return ret;
    }

    /**
     * 双指针
     *
     * @param s str
     * @return return
     */
    public static int lengthOfLongestSubstring1(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = s.toCharArray();
        int ret = 0;
        int fast = 0;
        for (int slow = 0; slow < chars.length; slow++) {
            if (slow != 0) {
                hashSet.remove(chars[slow - 1]);
            }
            for (int j = fast; j < chars.length; j++) {
                if (!hashSet.contains(chars[j])) {
                    ret = Math.max(j - slow + 1, ret);
                    hashSet.add(chars[j]);
                    fast++;
                } else {
                    break;
                }
            }
        }
        return ret;
    }
}
