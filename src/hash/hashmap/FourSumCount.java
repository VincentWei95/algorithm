package hash.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 454.四数相加
 *
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 示例 2：
 *
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 */
public class FourSumCount {

    /**
     * 1、先统计前两个数组每个元素两两之和 sum 出现的次数，用 map 存储（key=sum, value=count）
     * 2、再遍历后两个数组每个元素两两之和 sum，用 0-sum 作为 key 判断在 map 是否存在，如果存在从 map 获取出现的次数
     *
     * T:O(n2)
     * S:O(n)
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int sum = num1 + num2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int sum = num3 + num4;
                if (map.containsKey(0 - sum)) {
                    count += map.get(0 - sum);
                }
            }
        }

        return count;
    }
}
