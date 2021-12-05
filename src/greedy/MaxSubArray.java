package greedy;

/**
 * 53.最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 *
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaxSubArray {

    /**
     * 局部最优：
     * 负数只会拉低总和，所以计算的时候如果连续和为负数时立刻放弃
     * 从下一个元素重新计算连续和，因为负数加上下一个元素的连续和只会越来越小
     *
     * 全局最优：
     * 选取最大连续和
     *
     * 从局部最优可以推出全局最优。
     *
     * 思路：
     * 遍历数组 nums，定义变量 sum 记录连续和，如果 sum += nums[i] 为负数时，sum 重置为0
     * 因为是要记录最大的连续和，所以需要调整区间的终止位置，定义变量 result 记录最大连续和，当 sum > result 时更新最大连续和
     *
     * T:O(n)
     * S:O(1)
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > result) {
                result = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
        }
        return result;
    }
}
