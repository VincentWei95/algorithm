package dp;

/**
 * 343.整数拆分
 *
 * 给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36。
 *
 * 说明: 你可以假设n不小于 2 且不大于 58。
 */
public class IntegerBreak {

    /**
     * 1、确定 dp 数组及下标的含义
     * dp[i]：分拆数字 i，可以得到的最大乘积为 dp[i]
     *
     * 2、确定递推公式
     * 最大乘积 dp[i] 可以从 1 遍历 j，两种渠道的得到 dp[i]：
     * 一个是 j * (i-j) 直接相乘
     * 一个是 j * dp[i-j]，相当于是拆分 (i-j)
     *
     * j怎么就不拆分了？
     * j 是从 1 开始遍历，拆分 j 的情况，在遍历的过程中其实都计算过了
     * 那么从 1 遍历 j，比较 j * (i-j) 和 j * dp[i-j] 取最大，所以递推公式：
     * dp[i] = max(dp[i], max(j * (i-j), j * dp[i-j]))
     *
     * 也可以这么理解，j * (i-j) 是单纯的把整数拆分为两个数相乘，而 j * dp[i-j] 是拆分成两个以及两个以上的个数相乘
     * 如果定义 dp[j] * dp[i-j] 也是默认将一个数强制拆分成 4 份及以上了。所以递推公式：
     * dp[i] = max({dp[i], j * (i-j), j * dp[i-j]})
     *
     * 那么在取最大值的时候，为什么还要比较 dp[i] 呢？
     * 因为在递推公式推导过程中，每次计算 dp[i]，取最大的而已
     *
     * 3、确定 dp 初始化
     * dp[0]、dp[1] 应该初始化多少呢？
     * 有的题解里会给出 dp[0]=1，dp[1]=1 的初始化，但解释比较牵强，主要还是因为这么初始化可以把题目过了
     * 严格从 dp[i] 的定义来说，dp[0]、dp[1] 就不应该初始化，也就是没有意义的数值
     * 拆分 0 和拆分 1 的最大乘积是多少？这是无解的
     * 所以只初始化 dp[2]=1，从 dp[i] 的定义来说，拆分数字 2，得到的最大乘积是1
     *
     * 4、确定遍历顺序
     * 根据递推公式 dp[i] = max(dp[i], max(j * (i-j), j * dp[i-j]))
     * dp[i] 是依靠 dp[i-j] 推导的，所以遍历 i 一定是从前到后遍历
     * 枚举 j 的时候，是从 1 开始的。i 是从 3 开始，这样 dp[i-j] 就是 dp[2] 正好可以通过我们初始化的数值求出来
     * 所以遍历顺序为：
     * for (int i = 3; i <= n; i++) {
     *     for (int j = 1; j < i-1; j++) {
     *         dp[i] = max(dp[i], max(j * (i-j), j * dp[i-j]))
     *     }
     * }
     *
     * 5、举例推导 dp 数组
     * 举例当 n=10 时，dp 数组里的数值如下：
     *
     * 下标：  2  3  4  5  6  7  8  9  10
     * dp[i]：1  2  4  6  9  12 18 27 36
     *
     * T:O(n^2)
     * S:O(n)
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
