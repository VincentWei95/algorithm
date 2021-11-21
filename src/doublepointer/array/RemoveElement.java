package doublepointer.array;

/**
 * 27.移除元素
 *
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * 示例 2：
 *
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement {

    /**
     * 双指针
     *
     * 1、
     * [0, 1, 2, 2, 3, 0, 4, 2], val = 2
     * fast
     * slow
     *
     * fast = 0, slow = 0;
     * nums[fast] = 0 != val, nums[slow] = 0, slow++
     * [0, 1, 2, 2, 3, 0, 4, 2]
     * fast
     *   slow
     *
     * 2、
     * [0, 1, 2, 2, 3, 0, 4, 2], val = 2
     *   fast
     *   slow
     * fast = 1, slow = 1
     * nums[fast] = 1 != val, nums[slow] = 1, slow++
     * [0, 1, 2, 2, 3, 0, 4, 2]
     *    fast
     *      slow
     *
     * 3、
     * [0, 1, 2, 2, 3, 0, 4, 2], val = 2
     *      fast
     *      slow
     * fast = 2, slow = 2
     * nums[fast] = 2 == val
     *
     * 4、
     * [0, 1, 2, 2, 3, 0, 4, 2], val = 2
     *          fast
     *      slow
     * fast = 3, slow = 2
     * nums[fast] = 2 == val
     *
     * 5、
     * [0, 1, 2, 2, 3, 0, 4, 2], val = 2
     *            fast
     *      slow
     * fast = 4, slow = 2
     * nums[fast] = 3 != val, nums[slow] = 3, slow++
     * [0, 1, 3, 2, 3, 0, 4, 2]
     *            fast
     *         slow
     *
     * 6、
     * [0, 1, 3, 2, 3, 0, 4, 2]
     *               fast
     *         slow
     * fast = 5, slow = 3
     * nums[fast] = 0 != val, nums[slow] = 0, slow++
     * [0, 1, 3, 0, 3, 0, 4, 2]
     *               fast
     *            slow
     *
     * 7、
     * [0, 1, 3, 0, 3, 0, 4, 2]
     *                  fast
     *            slow
     * fast = 6, slow = 4
     * nums[fast] = 4 != val, nums[slow] = 4, slow++
     * [0, 1, 3, 0, 4, 0, 4, 2]
     *                  fast
     *               slow
     *
     * 8、
     * [0, 1, 3, 0, 4, 0, 4, 2]
     *                     fast
     *               slow
     * fast = 7, slow = 5
     * nums[fast] = 2 == val
     *
     * T:O(n)
     * S:O(1)
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
