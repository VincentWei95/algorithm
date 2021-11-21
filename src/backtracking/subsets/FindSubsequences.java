package backtracking.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 491.递增子序列
 *
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 *
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 * 示例 1：
 *
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 *
 * 示例 2：
 *
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
public class FindSubsequences {

    /**
     * 看到题目又是子集又是去重，之前的题目遇到这类题目会想到先对数组排序再做去重，就会掉进陷阱
     * 本题要求自增子序列，是不能对原数组进行排序的，排完序的数组都是自增子序列了，所以不能使用之前的去重逻辑
     *
     *
     *                                                递增子序列{}
     *                                             剩余集合{4,7,6,7}
     *                                /             \           \             \
     *                              取4             取7          取6           取7
     *                             /                 \            \             \
     *                        递增子序列{4}        递增子序列{7}   递增子序列{6}    同一父节点下
     *                       剩余集合{7,6,7}       剩余集合{6,7}    剩余集合{7}   本层重复使用7
     *                       /    \        \           \             \
     *                     取7    取6       取7         取6            取7
     *                    /        \         \           \             \
     *         递增子序列{4,7} 递增子序列{4,6} 同一父节点下  所取元素小于     递增子序列{6,7}
     *          剩余集合{6,7}  剩余集合{7}   本层重复使用7 子序列最后一个元素    剩余集合{}
     *               /              \
     *              取6             取7
     *             /                 \
     *          所取元素小于       递增子序列{4,6,7}
     *       子序列最后一个元素       剩余集合{}
     *
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 存储符合条件的结果集
     * 参数 List<Integer> path 记录经过的节点
     * 参数 int startIndex 收缩遍历范围
     * 不需要返回值 void
     *
     * 2、确定递归终止条件
     * 需要遍历整棵树所有节点，不需要终止条件，for 循环遍历完就完成递归
     * 题目要求自增子序列最少两个，path.size() >= 2 时将记录的节点存储到 result
     *
     * 3、确定单层递归逻辑
     * 题目要求存储的是自增子序列，path 在记录节点前，需要当前节点和 path 存储的最后一个节点数值进行对比，如果小于最后一个元素，不记录该节点
     * 在当前层如果使用过的元素相同也不记录，题目提供的 nums 取值范围在 [-100, 100]，可以使用数组作为hash表记录相同的元素数值是否使用过
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex) {
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
            // 这里不需要return，因为题目是需要递归整棵树
        }

        int[] numUsed = new int[201]; // 递归当前层的hash表
        for (int i = startIndex; i < nums.length; i++) {
            // 需要是递增子序列，如果存储节点的最后一个元素数值比当前的小，不处理
            if (!path.isEmpty()) {
                int lastNum = path.get(path.size() - 1);
                if (nums[i] < lastNum) {
                    continue;
                }
            }
            // 元素数值已经使用过，不处理
            if (numUsed[nums[i] + 100] == 1) continue;

            numUsed[nums[i] + 100] = 1; // 记录当前层的该元素数值已经有使用过
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
