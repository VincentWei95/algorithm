package doublepointer.array.replenish;

/**
 * 121.买卖股票的最佳时机：
 *
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit {

    public static void main(String[] args) {

    }

    /**
     * 方式1：暴力获取法，两层循环判断
     *
     * 每一个数组元素都和数组的所有元素每个元素遍历，对比相减后的差值
     *
     * T:O(n²)
     * S:O(1)
     */
    private int maxProfit1(int[] prices) {
        if (prices.length < 2) return 0;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[i] - prices[j]);
            }
        }
        return maxProfit;
    }

    /**
     * 方式2：
     *
     * 要实现最大利润即：买入最低的价格，获得最大的利润
     * 定义买入价格和最大利润变量，首次买入价格是第一个元素，最大利润为0，遍历数组
     * 如果元素小于当前买入价格，修改买入价格为这个元素
     * 如果元素大于当前买入价格，计算 元素值 - 当前买入价格 的差值大于当前最大利润，更新最大利润
     *
     * 9 3 7 5 1 8
     * buy  max
     * 9    0    当前元素为3，3 < 9，修改买入价格为3
     * 3    0    当前元素为7，7 > 3，计算差值7-3=4，4 > 0，更新最大利润为4
     * 3    4    当前元素为5，5 > 3，计算差值5-3=2，2 < 4，不处理
     * 3    4    当前元素为1，1 < 3，修改买入价格为1
     * 1    4    当前元素为8，8 > 1，计算差值8-1=7，7 > 4，更新最大利润为7
     *
     * 所以最大利润为7
     *
     * T:O(n)
     * S:O(1)
     */
    private int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0;
        int buy = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - buy);
            }
        }
        return maxProfit;
    }
}
