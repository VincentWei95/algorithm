package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56.合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge {

    /**
     * 思路：
     * 题目需要排序，那么按照左边界排序，还是右边界排序呢？其实都可以
     * 按照左边界排序，排序之后
     * 局部最优：每次合并都取最大的右边界，这样就可以合并更多的区间
     * 全局最优：合并所有重叠的区间
     * 局部最优推出全局最优，找不出反例，试试贪心
     *
     * 按照左边界从小到大排序之后，如果 intervals[i][0] < intervals[i-1][1] 即 当前左边界 < 上一个的右边界
     * 说明 intervals[i] 的左边界在 intervals[i-1] 的区间内，则一定重叠
     *
     * 知道如何判断重叠，剩下的就是合并了，如何去模拟区间合并呢？
     * 其实就是用合并区间后左边界和右边界，作为一个新的区间添加到 result 数组就可以了
     * 如果没有合并就把原区间添加到 result 数组
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int start = intervals[0][0];
        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > intervals[i - 1][1]) {
                // 没有重叠，将前面的区间合并
                result.add(new int[]{start, intervals[i - 1][1]});
                // 更新下一个可以合并的左边界
                start = intervals[i][0];
            } else {
                // 注意 <= 也算重叠，例如[1,4][4,5]
                // 当前左边界 < 上一个的右边界，有重叠
                // 更新最大右边界
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);

            }
        }
        // 添加最后一个
        result.add(new int[]{start, intervals[intervals.length - 1][1]});
        return result.toArray(new int[result.size()][]);
    }
}
