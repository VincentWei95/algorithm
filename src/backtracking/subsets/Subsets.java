package backtracking.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 78.子集
 *
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Subsets {

    /**
     *                      for 循环横向遍历
     *   递              ------------------>
     *   归                          子集{}
     *   纵                       剩余集合{1,2,3}
     *   向                  /         |        \
     *   遍              取元素1      取元素2    取元素3
     *   历              /             \         \
     *                得子集{1}      得子集{2}   得子集{3}
     *              剩余集合{2,3}    剩余集合{3}  剩余集合{}
     *              /      \           \
     *          取元素2     取元素3    取元素3
     *          /            \          \
     *       得子集{1,2}    得子集{1,3}  得子集{2,3}
     *      剩余集合{3}     剩余集合{}    剩余集合{}
     *      /
     *  取元素3
     *  /
     * 得子集{1,2,3}
     * 剩余集合{}
     *
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 存放所有结果集
     * 参数 List<Integer> path 存放走过的节点
     * 参数 int startIndex 收缩遍历集合范围，因为题目要求解集不重复
     * 不需要返回值 void
     *
     * 2、确定递归终止条件
     * 这道题目是遍历所有的节点，所以可以不需要终止条件，所有递归函数的 for 循环遍历完成即递归终止
     *
     * 3、确定单层递归逻辑
     * 因为需要遍历所有的节点，所以 for 循环时 path 添加每一个走过的节点，result 存储 path
     * 题目要求不能有重复，for 循环从 startIndex 作为开始索引，直到遍历结束
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex) {
        // 可以不写递归条件，因为是要遍历整棵树，所有节点遍历完也结束递归
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
