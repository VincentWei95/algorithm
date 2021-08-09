package math;

/**
 * 买卖股票的最大利润：
 *
 * 给你一个整数数组，其中第 i 个元素表示的是第 i 天的股票价格，你要计算出先买一股，然后再卖出它能获得的最大利润
 *
 * 比如说，给你的数组是：
 *
 * 9, 3, 7, 5, 1, 8
 *
 * 如果你在价格为 1 时买入并在价格为 8 时卖出，这时能获得最大的利润 7
 *
 * 再比如说给你的数组是：
 *
 * 9, 8, 7, 6
 *
 * 这时股票每天都在迭，不存在买入再卖出来获利的可能，因此没有交易，最大利润为 0
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
    private int maxProfilt1(int[] prices) {
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
