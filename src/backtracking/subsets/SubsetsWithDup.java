package backtracking.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90.子集2
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
 */
public class SubsetsWithDup {

    /**
     *                      for 循环横向遍历
     *   递              ------------------>
     *   归                          子集{}
     *   纵                       剩余集合{1,2,2}
     *   向                  /         |        \
     *   遍              取元素1      取元素2    取元素2
     *   历              /             \         \
     *                得子集{1}      得子集{2}   已经重复读取
     *              剩余集合{2,2}    剩余集合{2}
     *              /      \           \
     *          取元素2     取元素2    取元素2
     *          /            \          \
     *       得子集{1,2}  已经重复读取   得子集{2,2}
     *      剩余集合{2}                 剩余集合{}
     *      /
     *  取元素2
     *  /
     * 得子集{1,2,2}
     * 剩余集合{}
     *
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
     * 数组里面有重复的元素，为了去重，在递归前需要对数组进行排序，方便在递归时判断相邻节点是否重复
     * 在 for 循环时，先判断相邻的节点是否重复，如果重复则跳过
     * 因为需要遍历所有的节点，所以 for 循环时 path 添加每一个走过的节点，result 存储 path
     * 题目要求不能有重复解集，for 循环从 startIndex 作为开始索引，直到遍历结束
     */
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // 去重时的必要处理，需要先进行排序，否则无法处理相邻节点是否重复的判断
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) continue; // 去重，注意这里是 i > startIndex
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
