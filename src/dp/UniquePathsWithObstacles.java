package dp;

/**
 * 63.不同路径2
 *
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * ---------------------------------------
 * ｜start｜   ｜   ｜   ｜   ｜   ｜      ｜
 * ---------------------------------------
 * ｜     ｜   ｜   ｜   ｜   ｜   ｜      ｜
 * ---------------------------------------
 * ｜     ｜   ｜   ｜   ｜   ｜   ｜Finish｜
 *  -------------------------------------
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 示例1：
 *
 * ----------------------
 * ｜start｜   ｜        ｜
 * ----------------------
 * ｜     ｜ 1 ｜        ｜
 * ----------------------
 * ｜     ｜   ｜ Finish ｜
 * ----------------------
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例2：
 *
 * -----------------
 * ｜start｜ 1      ｜
 * -----------------
 * ｜     ｜ Finish ｜
 * -----------------
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 */
public class UniquePathsWithObstacles {

    /**
     * 思路：
     * {@link UniquePaths} 是没有障碍的情况，本题是有障碍情况，其实就是遇到障碍保持初始值为0即可
     *
     * 1、确定 dp 数组及下标 i 的含义
     * dp[i][j]：从 (0, 0) 出发到 (i, j) 有 dp[i][j] 条路径
     *
     * 2、确定递推公式
     * 和 {@link UniquePaths} 相同，dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 但要注意，遇到障碍物时保持初始值为0，所以代码为：
     * if (obstacleGrid[i][j] == 0) { // 没有障碍物再推导 dp
     *     dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * }
     *
     * 3、确定 dp 数组初始化
     * 在 {@link UniquePaths} 中初始化为：
     * for (int i = 0; i < m; i++) {
     *     dp[i][0] = 1;
     * }
     * for (int j = 0; j < n; j++) {
     *     dp[0][j] = 1;
     * }
     * 但如果 (i, 0) 这条边有了障碍，障碍往后的都走不到位置了，所以遇到障碍时就要跳出循环：
     * -------------------------------
     * | 1 | 1 | 1 | 障碍 | 0 | 0 | 0 |
     * -------------------------------
     * for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
     *     dp[i][0] = 1;
     * }
     * for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
     *     dp[0][j] = 1;
     * }
     *
     * 4、确定遍历顺序
     * 根据递推公式 dp[i][j] = dp[i-1][j] + dp[i][j-1]，遍历顺序从左到右
     *
     * 5、举例推导 dp 数组
     * 假设为例子1：
     * ----------------------
     * ｜ 1 ｜    1    ｜ 1 ｜
     * ----------------------
     * ｜ 1 ｜ 0（障碍）｜ 1 ｜
     * ----------------------
     * ｜ 1 ｜    1    ｜ 2 ｜
     * ----------------------
     *
     * T:O(m*n)
     * S:O(m*n)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
