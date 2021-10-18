package array.middle;

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * 给定一个包括n个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class ThreeSumClosest {

    /**
     * 1、
     * [-1, 2, 1, -4] -> [-4, -1, 1, 2]
     *
     * 2、
     * [-4, -1, 1, 2]
     *   i  l      r
     * result = 10 * 1000 * 1000, target = 1
     * int sum = nums[i] + nums[l] + nums[r] = (-4) + (-1) + 2 = -3
     * |sum - target| = |-3 - 1| = 4, |result - target| = 10 * 1000 * 1000, |sum - target| < |result - target|
     * sum = -3, result = 10 * 1000 * 1000, target = 1, sum更靠近target, result = sum = -3
     * sum < target, l++
     *
     * 3、
     * [-4, -1, 1, 2]
     *   i      l  r
     * result = -3, target = 1
     * int sum = nums[i] + nums[l] + nums[r] = (-4) + 1 + 1 = -2
     * |sum - target| = |-2 - 1| = 3, |result - target| = |-3 - 1| = 4, |sum - target| < |result - target|
     * sum = -2, result = -3, target = 1, sum更靠近target, result = sum = -2
     *
     * 4、
     * [-4, -1, 1, 2]
     *      i   l  r
     * result = -2, target = 1
     * int sum = nums[i] + nums[l] + nums[r] = (-1) + 1 + 2 = 2
     * |sum - target| = |2 - 1| = 1, |result - target| = |-2 - 1| = 3, |sum - target| < |result - target|
     * sum = 2, result = -2, target = 1, sum比result更靠近target, result = sum = 2
     *
     * T:O(n2)
     * n是数组nums的长度，首先需要O(nlogn)的时间对数组进行排序
     * 随后在循环的过程中，使用一重循环O(n)枚举a，双指针O(n)枚举b和c
     * 所以时间复杂度是O(n2)
     *
     * S:O(logn)
     * 排序需要使用O(logn)的空间
     * 修改了输入数组nums，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了nums的副本并进行排序
     * 此时空间复杂度为O(n)
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 10 * 1000 * 1000;
        for (int i = 0; i < nums.length; ++i) {
            // 如果相同则跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                // 匹配到一样的值，直接返回
                if (sum == target) {
                    return target;
                }
                // 如果数值更小，说明越接近target，更新数值
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    // 和当前的r对比，如果相同则跳过去重
                    int r1 = r - 1;
                    while (l < r1 && nums[r] == nums[r1]) {
                        r1--;
                    }
                    r = r1;
                } else {
                    // 和当前的l对比，如果相同则跳过去重
                    int l1 = l + 1;
                    while (l1 < r && nums[l] == nums[l1]) {
                        l1++;
                    }
                    l = l1;
                }
            }
        }
        return result;
    }
}
