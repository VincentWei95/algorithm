package greedy;

import java.util.Arrays;

/**
 * 1005.k次取反后最大化的数组和
 *
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 *
 * 选择某个下标 i并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 *
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 *
 * 示例 2：
 *
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 *
 * 示例 3：
 *
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 */
public class LargestSumAfterKNegations {

    /**
     * 思路：
     * 局部最优：让绝对值大的负数变为正数，当前数值达到最大
     * 全局最优：整个数组和达到最大
     *
     * 如果将负数都转变为正数 k 依然大于0，此时的问题是一个有序正整数序列如何转变k次正负，让数组和达到最大。
     *
     * 又是一个贪心：
     * 局部最优：只找数值小的正整数进行反转，当前数值可以达到最大
     * 全局最优：整个数组和达到最大
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        if (nums.length == 1) return k % 2 == 0 ? nums[0] : -nums[0];

        // 排序将负数放在前面
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 从前往后遍历，将负数都转换为正数
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        // 已经全部转换完，返回结果
        if (k == 0) return sum;

        // 还没有转换完，此时 nums 的元素都是正整数，排序后第一个元素就是最小的正整数数值，如果要累加负数就要求为最小数值
        Arrays.sort(nums);
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }
}
