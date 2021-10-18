package array.middle;

/**
 * 长度最小的子数组
 *
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */
public class MinSubArrayLen {

    /**
     * 1、定义两个指针 start 和 end 分别表示子数组的开始位置和结束位置，维护变量 sum 存储子数组中的元素和（即 nums[start] 到 nums[end] 的元素和）
     * 2、初始状态下，start 和 end 都指向下标0，sum 的值为0
     * 3、每一轮迭代，将 nums[end] 加到 sum
     * 如果 sum >= target，则更新子数组的最小长度（此时子数组的长度是 end - start + 1）
     * 然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum < target
     * 在此过程中同样更新子数组的最小长度
     * 在每一轮迭代的最后，将 end 右移
     *
     * 1、
     *   [2, 3, 1, 2, 4, 3]
     *  start
     *   end
     * start = 0, end = 0, sum = 0, target = 7
     * sum = sum + nums[end] = 0 + 2 = 2, sum < target, end = 1
     *
     * 2、
     *   [2, 3, 1, 2, 4, 3]
     *  start
     *      end
     * start = 0, end = 1, sum = 2, target = 7
     * sum = sum + nums[end] = 2 + 3 = 5, sum < target, end = 2
     *
     * 3、
     *   [2, 3, 1, 2, 4, 3]
     *  start
     *         end
     * start = 0, end = 2, sum = 5, target = 7
     * sum = sum + nums[end] = 5 + 1 = 6, sum < target, end = 3
     *
     * 4、
     *   [2, 3, 1, 2, 4, 3]
     *  start
     *            end
     * start = 0, end = 3, sum = 6, target = 7
     * sum = sum + sums[end] = 6 + 2 = 8
     * sum > target, ans = end - start + 1 = 3 - 0 + 1 = 4, sum = sum - nums[start] = 8 - 2 = 6, start = 1
     *
     *   [2, 3, 1, 2, 4, 3]
     *     start
     *            end
     * start = 1, end = 3, sum = 6, target = 7
     * sum < target, end = 4
     *
     * 5、
     *   [2, 3, 1, 2, 4, 3]
     *     start
     *               end
     * start = 1, end = 4, sum = 6, target = 7
     * sum = sum + nums[end] = 6 + 4 = 10
     * sum > target, ans = end - start + 1 = 4 - 1 + 1 = 4, sum = sum - nums[start] = 10 - 3 = 7, start = 2
     *
     *   [2, 3, 1, 2, 4, 3]
     *        start
     *               end
     * start = 2, end = 4, sum = 7, target = 7
     * sum = target, ans = end - start + 1 = 4 - 2 + 1 = 3, sum = sum - nums[start] = 7 - 1 = 6, start = 3
     *
     *   [2, 3, 1, 2, 4, 3]
     *           start
     *               end
     * start = 3, end = 4, sum = 6, target = 7
     * sum < target, end = 5
     *
     * 6、
     *   [2, 3, 1, 2, 4, 3]
     *           start
     *                  end
     * start = 3, end = 5, sum = 6, target = 7
     * sum = sum + nums[end] = 6 + 3 = 9
     * sum > target, ans = end - start + 1 = 5 - 3 + 1 = 3, sum = sum - nums[start] = 9 - 2 = 7, start = 4
     *
     *   [2, 3, 1, 2, 4, 3]
     *              start
     *                  end
     * start = 4, end = 5, sum = 7, target = 7
     * sum = target, ans = end - start + 1 = 5 - 4 + 1 = 2, sum = sum - nums[start] = 7 - 4 = 3, start = 5
     *
     * T:O(n)
     * S:O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0;
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
