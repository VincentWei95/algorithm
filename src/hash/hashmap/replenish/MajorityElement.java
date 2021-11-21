package hash.hashmap.replenish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169.多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 n/2 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */
public class MajorityElement {

    /**
     * 方案一：哈希表
     *
     * T:O(n)
     * S:O(n)
     */
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = nums.length / 2;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 方案二：排序
     *
     * 将数组排序后，下标为 n/2 的元素一定是众数，因为排序后相同的众数会是连续的
     *
     * T:O(nlogn)，数组排序的时间复杂度
     * S:O(logn)，使用语言自带的排序算法需要的栈空间；如果自己写堆排序，只需要O(1)的空间
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
