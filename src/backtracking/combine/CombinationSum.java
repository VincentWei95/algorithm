package backtracking.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39.组合总和
 *
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 *
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 *
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 *
 * 示例1：
 *
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 *
 * 示例2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 示例 4：
 *
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 *
 * 示例 5：
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {

    /**
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 记录符合条件的结果集
     * 参数 List<Integer> path 记录经过的节点
     * 参数 int sum 记录累加的节点数值，用于递归终止条件判断
     * 参数 int startIndex 缩减回溯时树的宽度范围
     * 返回值 void
     *
     * 2、确定递归终止条件
     * sum == target, return
     * sum + candidates[i] > target, break
     *
     * 3、确定单层递归逻辑
     * for 循环遍历 candidates
     * path 添加经过的节点
     * 因为题目提出可以无限重复被选取，所以 startIndex 在进入递归时是不会累加的，而是根据循环的索引 i 传递
     * 本层递归完后 path 撤销节点
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先进行排序
        backtracking(candidates, target, 0, 0);
        return result;
    }

    private void backtracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break; // 如果不符合条件就直接跳出本层循环，剪枝操作
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, sum, i); // 注意这里的 startIndex 传 i，因为可以无限重复被选取
            sum -= candidates[i]; // 回溯
            path.remove(path.size() - 1); // 回溯
        }
    }
}
