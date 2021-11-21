package hash.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 350.两个数组的交集2
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 */
public class Intersection2 {

    /**
     * 对于同一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值
     *
     * T:O(m+n)：m和n分别表示两个数字的长度
     * S:O(min(m, n))：map存储的长度最小的数组
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        // 用长度小的数组记录数字出现的次数，降低复杂度
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                result[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        // 因为 result 的长度是 nums1.length，可能最终存储的结果数量 < nums1.length
        // 就会导致未存储交集的数组位置默认都是0，所以只需要 0-index 存储交集的数据
        return Arrays.copyOfRange(result, 0, index);
    }
}
