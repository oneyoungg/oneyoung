package top.oneyoung.geektime.algorithm;

import java.util.Arrays;

/**
 * remove_duplicates_from_sorted_array_ii
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author oneyoung
 * @since 2023/6/28 15:23
 */
public class remove_duplicates_from_sorted_array_ii {
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1,1, 2};
        System.out.println("removeDuplicates(nums) = " + removeDuplicates(nums));
        System.out.println("nums = " + Arrays.toString(nums));
    }
}
