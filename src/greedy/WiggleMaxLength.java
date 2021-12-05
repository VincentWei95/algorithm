package greedy;

/**
 * 376.摆动序列
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 *
 * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
 *
 * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 *
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 *
 * 示例 1：
 *
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 *
 * 示例 2：
 *
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 *
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,5,6,7,8,9]
 * 输出：2
 */
public class WiggleMaxLength {

    /**
     * 1,7,4,9,2,5
     *                      9
     *                     / \
     *          7         /   \
     *         / \curDiff/     \
     *        /   \     /       \
     *       /     \   /         \        5
     *      /       \ /           \      /
     *     /         4             \    /
     *    / preDiff                 \  /
     *   /                           2
     *  /
     * 1
     *
     * 局部最优：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值
     * 整体最优：整个序列有最多的局部峰值，从而达到最长摆动序列
     * 局部最优推出全局最优，并举不出反例，那么试试贪心
     *
     * 实际操作上，其实连删除的操作都不用做，因为题目要求的是最长摆动子序列的长度，所以只需要统计数组的峰值数量就可以了
     * （相当于是删除单一坡度上的节点，然后统计长度）
     *
     * 本题代码实现中，还有一些技巧，例如统计峰值的时候，数组最左面和最右面是最不好统计的
     * 例如序列[2,5]，它的峰值数量是2，如果靠统计差值来计算峰值个数就需要考虑数组最左面和最右面的特殊情况
     * 所以可以针对序列[2,5]，假设为[2,2,5]，这样它就有坡度即 preDiff = 0：
     *
     *                 5
     *                /
     *               / curDiff = 3
     * 2 ---------- 2
     *  preDiff = 0
     *
     * 针对以上情形，result初始为1（默认最右面有一个峰值），此时 curDiff > 0 && preDiff <= 0，那么 result++（计算了左面的峰值）
     * 最后得到的 result 就是2（峰值个数为2即摆动序列长度为2）
     *
     * T:O(n)
     * S:O(1)
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;

        int preDiff = 0; // 当前一对差值
        int curDiff = 0; // 前一对差值
        int result = 1; // 峰值个数，默认序列最右边有一个峰值
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            // 出现一个坡度峰值
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }
}
