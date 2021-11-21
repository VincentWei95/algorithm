package halfsearch;

/**
 * 34.在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class SearchRange {

    /**
     * 假设定义左右边距分别为leftIndex和rightIndex
     *
     * leftIndex就是查找第一个 nums[mid] >= target 的索引
     * （怎么定义第一个 nums[mid[ >= target 的索引？因为数组是升序的，第一个 nums[mid] >= target 的索引肯定会在左边，二分法往左区间走的情况就是 right 指针递减时，
     * 所以应该理解为 找到二分法搜索到最后一个 nums[mid] == target 的索引）
     *
     * rightIndex就是查找第一个 nums[mid] >target 的索引-1
     * （怎么定义第一个 > target 的索引？因为数组是升序的，第一个 nums[mid] > target 的索引应该理解为 找到二分法搜索到第一个 nums[mid] == target 的索引）
     *
     *  [5, 7, 7, 8, 8, 10], target = 8, ans = nums.length = 6
     * left            right
     *
     * 1、
     *  [5, 7, 7, 8, 8, 10], target = 8, ans = 6
     * left   mid      right
     *
     * left = 0, right = 5
     * mid = (left + right) / 2 = 5 / 2 = 2
     * nums[mid] = 7 < target, left = mid + 1 = 2 + 1 = 3
     *
     * 2、
     *  [5, 7, 7, 8,  8,  10], target = 8, ans = 6
     *          left mid right
     *
     * left = 3, right = 5
     * mid = (left + right) / 2 = 8 / 2 = 4
     * nums[mid] = 8 == target, right = mid - 1 = 5 - 1 = 4, ans = mid = 4
     *
     * 3、
     *  [5, 7, 7, 8,   8,  10], target = 8, ans = 4
     *          left right
     *          mid
     * left = 3, right = 4
     * mid = (left + right) / 2 = 7 / 2 = 3
     * nums[mid] = 8 == target, right = mid - 1 = 3 - 1 = 2, ans = mid = 3
     *
     * 第一个nums[mid] >= target 的索引为 ans = 3，所以 leftIndex = 3
     *
     * 4、按上面的步骤，得出rightIndex = 4
     *
     * T:O(logn)
     * S:O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length
                && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[] {leftIndex, rightIndex};
        }
        return new int[] {-1, -1};
    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
