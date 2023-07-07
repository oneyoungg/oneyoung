package top.oneyoung.geektime.algorithm;

/**
 * remove_element
 *
 * @author oneyoung
 * @since 2023/6/28 14:52
 */
public class remove_element {
    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        while (j < nums.length){
            if (nums[j] != val){
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}
