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
     * 需要考虑三种情况：
     * 1、target在数组范围的右边或者左边，target在左边。例如数组[3,4,5]，target=2，此时返回[-1,-1]
     * 2、target在数组范围中，且数组不存在target。例如数组[3,6,7]，target=5，此时返回[-1,-1]
     * 3、target在数组范围中，且数组中存在target，例如数组[3,6,7]，target=6，此时返回[1,1]
     *
     * T:O(logn)
     * S:O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);

        // 情况1
        if (leftBorder == -2 || rightBorder == -2) {
            return new int[] {-1, -1};
        }

        // 情况3
        if (rightBorder - leftBorder > 1) {
            return new int[] {leftBorder + 1, rightBorder - 1};
        }

        // 情况2
        return new int[] {-1, -1};
    }

    private int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) { // 寻找左边界，那么右区间要往左边收缩范围
                right = mid - 1;
                leftBorder = right;
            } else {
                left = mid + 1;
            }
        }
        return leftBorder;
    }

    private int getRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // 找右边界，左区间需要往右边收缩范围
                left = mid + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }
}
