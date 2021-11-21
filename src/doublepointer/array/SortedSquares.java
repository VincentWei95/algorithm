package doublepointer.array;

import java.util.Arrays;

/**
 * 977.有序数组的平方
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class SortedSquares {

    /**
     * 暴力解法
     *
     * T:O(n + nlogn)
     * S:O(1)
     */
    public int[] sortedSquares1(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 双指针
     *
     * 因为数组已经是有序的，只不过负数平方之后可能成为最大数了
     * 那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能在中间
     *
     * 此时可以考虑双指针法，i指向起始位置，j指向终止位置
     * 定义一个新数组result，和nums数组一样大小，让k指向数组终止位置
     * 1、如果 nums[i] * nums[i] < nums[j] * nums[j], result[k--] = nums[j] * nums[j], j--
     * 2、如果 nums[i] * nums[i] > nums[j] * nums[j], result[k--] = nums[i] * nums[i], i++
     *
     * 1、
     * [-4,-1,0,3,10]
     *  i          j
     *             k
     * i = 0, j = 4, k = 4
     * nums[i] * nums[i] = 16 < nums[j] * nums[j] = 100, result[k] = 100, j--=3, k--=3
     * result = [0, 0, 0, 0, 100]
     *
     * 2、
     * [-4,-1,0,3,10]
     *  i       j
     *          k
     * i = 0, j = 3, k = 3
     * nums[i] * nums[i] = 16 > nums[j] * nums[j] = 9, result[k] = 16, i++=1, k--=2
     * result = [0, 0, 0, 16, 100]
     *
     * 3、
     * [-4,-1,0,3,10]
     *     i    j
     *        k
     * i = 1, j = 3, k = 2
     * nums[i] * nums[i] = 1 < nums[j] * nums[j] = 9, result[k] = 9, j--=2, k--=1
     * result = [0, 0, 9, 16, 100]
     *
     * 4、
     * [-4,-1,0,3,10]
     *     i  j
     *     k
     * i = 1, j = 2, k = 1
     * nums[i] * nums[i] = 1 < nums[j] * nums[j] = 0, result[k] = 1, i++=2, k--=0
     * result = [0, 1, 9, 16, 100]
     *
     * 5、
     * [-4,-1,0,3,10]
     *       j
     *       i
     *  k
     * i = 2, j = 2, k = 0
     * nums[i] * nums[i] = 0 < nums[j] * nums[j] = 0, result[k] = 0, i++=2, k--=-1
     * result = [0, 1, 9, 16, 100]
     *
     * T:O(n)
     * S:O(1)
     */
    public int[] sortedSquares2(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int[] result = new int[nums.length];
        int k = nums.length - 1;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int leftSquare = nums[i] * nums[i];
            int rightSquare = nums[j] * nums[j];
            if (leftSquare < rightSquare) {
                result[k--] = rightSquare;
                j--;
            } else {
                result[k--] = leftSquare;
                i++;
            }
        }
        return result;
    }
}
