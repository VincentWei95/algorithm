package doublepointer.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54.螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 *
 * 1 -> 2 -> 3
 *           |
 * 4 -> 5    6
 * |         |
 * 7 <- 8 <- 9
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 *
 * 1 ->  2  -> 3 -> 4
 *                  |
 * 5 ->  6 ->  7    8
 * |                |
 * 9 <- 10 <- 11 <- 12
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralOrder {

    /**
     * 首先需要先理解矩阵哪里对应的行和列
     *      column
     * row -----------> x
     *     | 1  2  3
     *     | 4  5  6
     *     | 7  8  9
     *     y
     *
     * 将矩阵类比为横纵坐标，row 从左到右类比为 x 坐标的递增，column 从上到下类比为 y 坐标的递增
     *
     * 当访问矩阵元素时，即 matrix[y][x]
     *
     * 用坐标的方式类比：
     *
     * (left, top)      (right, top)
     *     。--------------------->。
     *     |
     *     |
     *     |
     *     |
     *     |
     *     。                      。
     * (left, bottom)   (right, bottom)
     *
     * 当我们要操作 x 坐标即 left/right 时，对应的就是遍历矩阵的列 column，即 int column = matrix[y].length
     * 当我们要操作 y 坐标即 top/bottom 时，对应的就是遍历矩阵的行 row，即 int row = matrix.length
     *
     * 初始坐标为：
     *
     * (0, 0)               (column - 1, 0)
     *     。--------------------->。
     *     |
     *     |
     *     |
     *     |
     *     |
     *     。                      。
     * (0, row - 1)         (column - 1, row - 1)
     *
     * 有了上述矩阵的概念，可以画一张图，从矩阵的外层到内层遍历按图的箭头方向遍历矩阵
     *
     * (left, top)      (right, top)
     *      1   ->  2   ->   3
     *                       |
     *      4   ->  5        6
     *      |                |
     *      7   <-  8  <-   9
     * (left, bottom)   (right, bottom)
     *
     *  int left = 0;
     *  int top = 0;
     *  int right = columns.length - 1;
     *  int bottom = rows.length - 1;
     *
     *  1、从左到右：x 坐标变化，y 坐标不变，即 x 坐标 left -> right，y = top， matrix[top][left->right]
     *  2、从上到下：x 坐标不变，y 坐标变化，即 y 坐标 top+1 -> bottom，x = right，matrix[top+1->bottom][right]
     *  3、从右到左：x 坐标变化，y 坐标不变，即 x 坐标 right-1 -> left - 1，y = bottom，matrix[bottom][right-1->left - 1]
     *  4、从下到上：x 坐标不变，y 坐标变化，即 y 坐标 bottom -> top - 1, x = left，matrix[bottom->top - 1][left]
     *
     * T:O(mn)，m和n分别为矩阵的行和列数，矩阵中每个元素都会被访问一遍
     * S:P(1)
     *
     *  详解：
     *  https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode-solution/
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                order.add(matrix[top][i]);
            }
            // 从上到下
            for (int i = top + 1; i <= bottom; i++) {
                order.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                // 从右到左
                for (int i = right - 1; i > left; i--) {
                    order.add(matrix[bottom][i]);
                }
                // 从下到上
                for (int i = bottom; i > top; i--) {
                    order.add(matrix[i][left]);
                }
            }

            // 向内收缩范围
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 示例 2：
     *
     * 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * T:O(mn)
     * S:O(1)
     */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];

        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;

        int[] result = new int[rows * columns];
        int index = 0;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                result[index++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    result[index++] = matrix[bottom][i];
                }
                for (int i = bottom; i > top; i--) {
                    result[index++] = matrix[i][left];
                }
            }

            left++;
            right--;;
            top++;
            bottom--;
        }
        return result;
    }
}
