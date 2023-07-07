package oneyoung.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LongestNiceSubstring
 *
 * @author oneyoung
 * @since 2022/2/1 5:57 PM
 */
public class LongestNiceSubstring {

    public static void main(String[] args) {
        removeElement(new int[]{3, 2, 2, 3}, 3);

    }

    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            System.out.println(Arrays.toString(nums) + " right = " + right + "  left = " + left);
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int re = target - num;
            if (map.containsKey(re)) {
                return new int[]{map.get(re), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[]{};
    }

}
