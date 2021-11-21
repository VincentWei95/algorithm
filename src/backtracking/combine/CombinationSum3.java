package backtracking.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 216.组合总和 3
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1-9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {

    /**
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 记录所有符合条件的结果集
     * 参数 List<Integer> path 记录递归存储的每个数值
     * 参数 sum 累加每个数值的和
     * 参数 startIndex 记录本层递归中，集合从哪里开始遍历
     * 回溯函数一般不需要返回值
     *
     * 2、确定递归函数终止条件
     * 当 path.size() == k 时，如果 sum == n，则将 path 添加到 result 后返回
     * 当 sum > n 时 且 path.size() < k 时，返回
     *
     * 3、确定单层递归逻辑
     * for 循环每次从 startIndex 开始遍历
     * sum 累加每个数值，path 也添加数值
     * 进入递归取下一个数值
     * 递归完后，sum 撤回累加的数值，path 同样也撤回添加的数值
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 0, 1);
        return result;
    }

    private void backtracking(int k, int n, int sum, int startIndex) {
        if (sum > n) return;
        if (path.size() == k) {
            if (sum == n) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            sum += i;
            path.add(i);
            backtracking(k, n, sum, i + 1);
            path.remove(path.size() - 1);
            sum -= i;
        }
    }
}
