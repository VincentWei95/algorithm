package doublepointer.matrix;

/**
 * 59.螺旋矩阵2
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例1：
 *
 * 1 -> 2 -> 3
 *           |
 * 8 -> 9    4
 * |         |
 * 7 <- 6 <- 5
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 */
public class GenerateMatrix {

    /**
     * T:O(n2)
     * S:O(n)
     *
     * 题解具体可以参考 {@link SpiralOrder}
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int count = 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = count++;
            }
            for (int i = top + 1; i <= bottom; i++) {
                matrix[i][right] = count++;
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    matrix[bottom][i] = count++;
                }
                for (int i = bottom; i > top; i--) {
                    matrix[i][left] = count++;
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;
    }
}
