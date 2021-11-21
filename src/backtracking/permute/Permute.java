package backtracking.permute;

import java.util.ArrayList;
import java.util.List;

/**
 * 46.全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permute {

    /**
     *                                   集合{1,2,3}
     *                         /        \                 \
     *                      取1          取2               取3
     *                      /             \                 \
     *                得排列{1}           得排列{2}           得排列{3}
     *               剩余集合{2,3}      剩余集合{1,3}         剩余集合{1,2}
     *               /      \            \        \          \         \
     *              取2     取3           取1      取3        取1        取2
     *             /         \             \        \         \          \
     *          得排列{1,2}   得排列{1,3} 得排列{2,1} 得排列{2,3} 得排列{3,1} 得排列{3,2}
     *          剩余集合{3}   剩余集合{2}  剩余集合{3}  剩余集合{1} 剩余集合{2} 剩余集合{1}
     *         /               \             \         \        \           \
     *        取3              取2            取3       取1       取2         取1
     *       /                  \             \         \         \           \
     *    排列{1,2,3}        排列{1,3,2}   排列{2,1,3}  排列{2,3,1} 排列{3,1,2} 排列{3,2,1}
     *
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 记录符合条件的结果集
     * 参数 List<Integer> path 记录经过的节点
     * 返回值 void
     *
     * 2、确定递归终止条件
     * 当到达叶子节点时就结束递归，path 记录节点的长度和数组 nums 相等时
     *
     * 3、确定单层递归逻辑
     * 排列问题每次都要从头开始搜索，for 循环的开始索引都是从0开始
     * 添加节点前判断当前节点是否已经记录在 path，一个排列里一个元素只能使用一次
     * 循环体 path 记录每个节点，递归后回溯
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums);
        return result;
    }

    private void backtracking(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue; // 已经记录了元素，跳过

            path.add(nums[i]);
            backtracking(nums);
            path.remove(path.size() - 1);
        }
    }
}
