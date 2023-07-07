package oneyoung.zingfront;

import java.util.ArrayList;
import java.util.List;

/**
 * 每次优化其实就是从数组中选出最大值，然后最大值-3，其他元素+1。
 * 发现后买你出现了循环，第一次出现循环时，所有的组合都已经出现，只需要取模获取值即可
 *
 * @author oneyoung
 * @date 2020/10/12 012 21:33
 */

public class DepartmentOptimization {

    public static void main(String[] args) {
        optimizeLogic();
    }

    public static void optimizeLogic() {
        // 需要优化的次数
        int count = 120;
        // 初始部门人数数组
        int[] departmentStaffs = {10, 7, 5, 4};
        // 每次优化结果存入LIST
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder num = new StringBuilder();
            optimize(departmentStaffs);
            for (int departmentStaff : departmentStaffs) {
                num.append(departmentStaff);
            }
            list.add(num.toString());
            // 当列表出现重复组合时 说明已经开始循环 直接算出结果
            if (list.stream().filter(s -> s.equals(num.toString())).count() > 1) {
                System.out.println(list.get(count % list.lastIndexOf(num.toString())));
                break;
            }
        }
    }

    /**
     * 优化的逻辑
     *
     * @param departmentStaffs 目标数组
     */
    private static void optimize(int[] departmentStaffs) {
        int index = getMaxIndex(departmentStaffs);
        for (int i = 0; i < departmentStaffs.length; i++) {
            if (index == i) {
                departmentStaffs[i] -= 3;
            } else {
                departmentStaffs[i] += 1;
            }
        }
    }

    /**
     * 获取数组最大值的索引
     *
     * @param arr 目标数据
     * @return 最大值的索引
     */
    private static int getMaxIndex(int[] arr) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

}
