package greedy;

/**
 * 134.加油站
 *
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 示例1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class CanCompleteCircuit {

    /**
     * 思路：
     * 如果 总油量 - 总消耗 大于等于零，那么一定可以跑完一圈，说明 各个站点的加油站剩油量 rest[i] 相加一定是大于等于零的
     * 每个加油站的剩余量 rest[i] = gas[i] - cost[i]
     * i 从0开始累加 rest[i]，和记为 curSum，一旦 curSum < 0，说明 [0,i] 区间都不能作为起始位置，起始位置从 i+1 算起，再从 0 计算 curSum
     *
     * 下标： 0  1  2  3  4
     * gas： 1  2  3  4  5
     * cos： 3  4  5  1  2
     * 剩余：-2 -2 -2  3  3
     *                |
     *              起始位置
     *
     * 为什么一旦[i,j]区间的和为负数，起始位置就可以是 j+1 呢，j+1 后面就不会出现更大的负数？
     * 如果出现更大的负数，就是更新 j，那么起始位置又变成新的 j+1
     * 而且 j 之前出现了多少负数，j 后面就会出现多少正数，因为耗油总和是大于零的（前提已经确定了一定可以跑完全程）
     *
     * 局部最优：当前累加 rest[j] 的和 curSum 一旦小于0，起始位置至少要是 j+1，因为从 j 开始一定不行
     * 全局最优：找到可以跑一圈的起始位置
     *
     * 局部最优能推出全局最优，找不出反例，试试贪心
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += (gas[i] - cost[i]);
            totalSum += (gas[i] - cost[i]);
            if (curSum < 0) {
                start = i + 1; // 更新起始位置
                curSum = 0;
            }
        }
        // totalSum < 0 说明怎么走都不能跑一圈
        return totalSum < 0 ? -1 : start;
    }
}
