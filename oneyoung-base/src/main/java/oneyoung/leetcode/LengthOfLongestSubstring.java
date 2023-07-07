package oneyoung.leetcode;

/**
 * LengthOfLongestSubstring
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author oneyoung
 * @since 2022/3/2 6:13 PM
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        int abcabcbb = lengthOfLongestSubstring("abcabcbb");
        System.out.println(abcabcbb);
    }

    public static int lengthOfLongestSubstring(String s) {
        System.out.println(s);
        char[] chars = s.toCharArray();
        int fast = 0;
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = fast; j < chars.length; j++) {
                if (i == j) {
                    continue;
                }
                String substring = s.substring(i, fast + 1);
                String current = String.valueOf(chars[j]);
                System.out.println("substring = " + substring);
                System.out.println("current = " + current);
                System.out.println("==============");
                if (!substring.contains(current)) {
                    fast = j;
                    result = Math.max(result, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return result;
    }
}
