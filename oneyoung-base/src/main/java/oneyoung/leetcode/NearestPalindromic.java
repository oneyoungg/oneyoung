package oneyoung.leetcode;

/**
 * NearestPalindromic
 *
 * @author oneyoung
 * @since 2022/3/2 4:54 PM
 */
public class NearestPalindromic {
    public static void main(String[] args) {
        String s = nearestPalindromic("12345");
        System.out.println(s);
    }

    public static String nearestPalindromic(String n) {
        int length = n.length();
        if (length == 1) {
            return String.valueOf(Integer.parseInt(n) - 1);
        }
        char[] chars = n.toCharArray();
        Character midChar = ((length & 1) == 1) ? chars[length >> 1] : null;
        int mid = length >> 1;
        String substring = n.substring(0, mid);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(substring);
        if (midChar != null) {
            stringBuilder.append(midChar);
        }
        char[] sub = substring.toCharArray();
        for (int i = sub.length - 1; i >= 0; i--) {
            stringBuilder.append(sub[i]);
        }
        if (stringBuilder.length() == 2) {
            return "9";
        }
        if (stringBuilder.charAt(0) == stringBuilder.charAt(1)) {
            return String.valueOf(Integer.parseInt(n) - 1);
        }
        return stringBuilder.toString();
    }
}
