package doublepointer.array;

/**
 * 26.删除有序数组的重复项
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {

    /**
     *
     * 1、如果数组 nums 的长度为0， 则数组不包含任何元素，返回0
     * 2、当数组 nums 的长度大于0，数组中至少包含一个元素，在删除重复元素之后也至少剩下一个元素，因此 nums[0] 保持原状即可，从下标1开始删除重复元素
     * 3、定义两个指针 fast 和 slow 分别为快指针和慢指针，快指针表示遍历数组到达的下标位置，慢指针表示下一个不同元素要填入的下标位置，初始化时两个指针都指向下标1
     * （1）
     * 假设数组 nums 长度为 n，将快指针 fast 依次遍历从 1 到 n-1 的每个位置
     * 对于每个位置，如果 nums[fast] != nums[fast -1]，说明 nums[fast] 和之前的元素都不同，因此将 nums[fast] 的值复制到 nums[slow]，然后将 slow 的值加1
     * （2）
     * 遍历结束之后，从 nums[0] 到 nums[slow - 1] 的每个元素都不相同且包含原数组中的每个不同的元素，因此新的长度即为slow，返回 slow 即可
     *
     * 1、
     * [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
     *    fast
     *    slow
     *
     * 2、
     * [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
     *       fast
     *    slow
     * [0, 1, 1, 1, 1, 2, 2, 3, 3, 4]
     *       fast
     *       slow
     *
     * 3、
     * [0, 1, 1, 1, 1, 2, 2, 3, 3, 4]
     *                fast
     *       slow
     * [0, 1, 2, 1, 1, 2, 2, 3, 3, 4]
     *                fast
     *       slow
     *
     * 4、
     * [0, 1, 2, 1, 1, 2, 2, 3, 3, 4]
     *                      fast
     *          slow
     * [0, 1, 2, 3, 1, 2, 2, 3, 3, 4]
     *                      fast
     *             slow
     *
     * 5、
     * [0, 1, 2, 3, 1, 2, 2, 3, 3, 4]
     *                            fast
     *             slow
     * [0, 1, 2, 3, 4, 2, 2, 3, 3, 4]
     *                            fast
     *                slow
     *
     * T:O(n)
     * S:O(1)
     */
    public int removeDumplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int fast = 1;
        int slow = 1;
        int length = nums.length;
        while (fast < length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public int removeDumplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                slow++; // 要先往前走一步，然后才开始交换
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
