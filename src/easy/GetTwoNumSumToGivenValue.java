package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 求和为给定值的两个数：
 *
 * 给你一个整数数组和一个目标值，你要找到数组里两个整数，它们的和等于目标值，返回这两个整数的下标
 *
 * 比如数组 [1, 2, 3, 6, 8, 11]，目标值为10，那么数组数值2+8=10，所以需要返回这两个数值的角标[1,4]
 */
public class GetTwoNumSumToGivenValue {

    public static void main(String[] args) {
        GetTwoNumSumToGivenValue main = new GetTwoNumSumToGivenValue();
        int[] result = main.getTwoNumSumToGivenValueHash(new int[]{1, 2, 3, 6, 8, 11}, 10);
        for (int index : result) {
            System.out.println(index);
        }
    }

    /**
     * 方式1：暴力解法，类似选择排序方式一个个相加后和整数对比判断是否相等
     *
     * T:O(n²) 两层循环，最差情况要全部遍历完成
     * S:O(1)
     */
    public int[] getTwoNumSumToGivenValue(int[] arr, int target) {
        for (int out = 0; out < arr.length; out++) {
            for (int in = out + 1; in < arr.length; in++) {
                if ((arr[out] + arr[in]) == target) {
                    return new int[] { out, in };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * 方式2：哈希表解法
     *
     * key为数组下标对应的值arr[i]，value为下标值i
     * 循环数组时判断当前获取的数组值arr[i]和哈希表中的某个数值相加是否等于target
     *
     * T:O(n) 只使用一个循环
     * S:O(n) 因为使用了哈希表存储
     */
    public int[] getTwoNumSumToGivenValueHash(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int numNeeded = target - arr[i]; // 与当前数组下标值相加后等于target的所需数值
            if (map.containsKey(numNeeded)) {
                return new int[] {map.get(numNeeded), i};
            }
            map.put(arr[i], i);
        }
        return new int[] { -1, -1 };
    }
}
