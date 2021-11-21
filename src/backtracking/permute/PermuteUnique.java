package backtracking.permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47.全排列2
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermuteUnique {

    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // 先排序，方便去重对比判断
        boolean[] used = new boolean[nums.length];
        backtracking(nums, used);
        return result;
    }

    private void backtracking(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过，这里用树层判断效率更高
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // 如果同⼀树⽀nums[i]没使⽤过开始处理
            if (!used[i]) {
                used[i] = true; // 标记同⼀树⽀nums[i]使⽤过，防止同一树支重复使用
                path.add(nums[i]);
                backtracking(nums, used);
                path.remove(path.size() - 1); // 回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false; // 回溯
            }
        }
    }
}
