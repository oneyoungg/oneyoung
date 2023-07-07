package top.oneyoung.geektime.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Two
 *
 * @author oneyoung
 * @since 2023/6/26 17:53
 */
public class Two {

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < length; i++){
            int v = target - nums[i];
            if (map.get(v) != null){
                return new int[]{map.get(v), i};
            }else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
