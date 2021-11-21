package backtracking.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 77.组合
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= k <= n
 */
public class Combine {

    /**
     * 思路：
     * 假设当 n = 4，k = 2 时，很容易想到用两个 for 循环：
     *
     * int n = 4;
     * for (int i = 1; i <= n; i++) {
     *     for (int j = i + 1; j <=n; j++) {
     *         // ...
     *     }
     * }
     *
     * 当 n = 100, k = 3，就是三层 for 循环：
     *
     * int n = 100;
     * for (int i = 1; i <= n; i++) {
     *     for (int j = i + 1; j <= n; j++) {
     *         for (int u = j + 1; u <= n; u++) {
     *
     *         }
     *     }
     * }
     *
     * 当 n = 100, k = 50 时，就是 50 层的 for 循环！
     *
     * 为了解决嵌套多层 for 循环，使用回溯法就用递归来解决嵌套层数的问题
     * 递归来做层叠嵌套（可以理解是开 K 层 for 循环），每一次递归中嵌套一个 for 循环，那么递归就可以用于解决多层嵌套循环的问题
     *
     * 回溯法解决的问题都可以抽象为树形结构（N叉树），可以用树型结构理解回溯过程：
     *
     * n = 4, k = 2
     *                          从左向右取，取过的数不再重复取
     *                         --------------------------->
     *                                 在1,2,3,4中取两个数
     *                     /             \            \            \
     *                   取1              取2          取3          取4
     *                  /                  \            \           \
     *               在2,3,4中取一个数   在3,4中取一个数  在4中取一个数   空
     *               /     |     \           \     \        \
     *             取2    取3     取4         取3   取4      取4
     *            /       |        \          \     \        \
     *         [1,2]    [1,3]     [1,4]       [2,3] [2,4]    [3,4]
     *
     * 每次从集合中选取元素，可选择的范围随着选择进行而收缩，调整可选择的范围
     * 可以分析出 n 表示的树的宽度，k 表示树的深度
     *
     * 如何在这个树上遍历，然后手机到我们要的结果集呢？
     * 根据上面树形结构的分析，每次搜索到了叶子节点，我们就找到了一个结果
     * 相当于只需要把达到叶子节点的结果收集起来，就可以求得 n 个数中 k 个数的组合集合
     *
     * 1、确定递归函数参数和返回值
     * （1）需要两个变量存放符合条件的单一结果 List<Integer> path，和到达叶子节点时符合条件的结果集合 List<List<Integer>> result。为了递归函数少一些参数，就将这两个参数定义为全局变量
     * （2）递归参数还需要传入 n 和 k
     * （3）int 类型变量 startIndex，记录在本层递归中，集合从哪里开始遍历
     *
     * 2、确定递归函数的终止条件
     * 当 path 的结果集长度等于 k 时，说明找到了一个符合条件的子集大小，对应的就是到达叶子节点
     *
     * 3、确定递归函数的遍历过程
     * for 循环每次从 startIndex 开始遍历
     * 先用 path 保存取到的节点
     * 再次进入递归取下一个节点
     * 本层递归完成后，path 要回退撤销保存的节点
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>(); // 命名为path主要是因为回溯就是树形结构，收集的子集合就是树的路径
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;
    }

    private void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // [startIndex, n] = [1, 4]
        // i <= n 虽然也能实现回溯，但会存在无效递归的情况
        // 假设 n = 4, k = 4，除了[1,2,3,4]，回溯时再遍历保存取其他节点到 path 也已经不符合 path.size == k 的条件

        // path.size()：已经选择的元素个数
        // k - path.size()：还需要的元素个数
        // n - (k - path.size() + 1：在集合 n 中至多要从该起始位置开始遍历。+1 是因为包括起始位置，我们要一个左闭的集合。
        // 假设 n = 4，k = 3，目前已经选取的元素为0（即 path.size() 为 0），n - (k - path.size()) + 1 = 4 - (3 - 0) + 1 = 2，从2开始搜索都是合理的，可以是组合[2,3,4]

        // i <= n - (k - path.size()) + 1，对回溯做剪枝操作，不符合条件的递归不处理
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i); // 保存节点
            backtracking(n, k, i + 1); // 回溯，startIndex 要 + 1
            path.remove(path.size() - 1); // 撤销保存的节点
        }
    }
}
