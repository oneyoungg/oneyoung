package top.oneyoung.geektime.algorithm;

import java.util.Arrays;

/**
 * remove_duplicates_from_sorted_array
 *
 * @author oneyoung
 * @since 2023/6/28 14:58
 */
public class remove_duplicates_from_sorted_array {

    public static int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                fast++;
            } else {
                nums[++slow] = nums[fast];
            }
        }
        return slow++;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2,3,3,4};
        int i = removeDuplicates(nums);
        System.out.println("i = " + i);
        System.out.println(Arrays.toString(nums));
    }
}
