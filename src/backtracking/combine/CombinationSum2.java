package backtracking.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40.组合总和2
 *
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * 注意：解集不能包含重复的组合。
 *
 * 示例1:
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例2:
 *
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 提示:
 *
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSum2 {

    /**
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 存储符合条件的结果集
     * 参数 List<Integer> path 记录经过的节点
     * 参数 int sum 累加节点数值用于递归终止条件判断
     * 参数 int startIndex 收缩回溯的遍历范围
     *
     * 2、确定递归终止条件
     * sum == target，return
     * sum > target, break
     *
     * 3、确定单层递归逻辑
     * for 循环从 startIndex 开始遍历 candidates
     * 如果 sum > target，跳出循环
     * 题目有条件 candidates 每个数字只能在组合使用一次，所以在递归前需要对数组排序，
     * 在循环体还需要去重 i > startIndex && candidates[i] == candidates[i - 1]，找下一个节点
     * sum += candidates[i], path.add(candidates[i])
     * 进入递归函数，startIndex 需要 + 1
     * 递归完后回溯，sum -= candidates[i], path.remove(path.size() - 1)
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 需要先进行排序，方便递归时判断排除是否重复添加
        backtracking(candidates, target, 0, 0);
        return result;
    }

    private void backtracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue; // 去重
            sum += candidates[i];
            path.add(candidates[i]);
            backtracking(candidates, target, sum, i + 1);
            sum -= candidates[i]; // 回溯
            path.remove(path.size() - 1); // 回溯
        }
    }
}
