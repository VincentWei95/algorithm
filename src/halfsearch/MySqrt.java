package halfsearch;

/**
 * 69.x的平方根
 *
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 */
public class MySqrt {

    /**
     * 平方根的概念：
     *
     * 如果一个非负数k的平方等于x，即k2=x(x >= 0)，那么这个非负数k叫做x的算数平方根
     * x的算数平方根记为根号x，x叫做被开平方数，求一个非负数x的平方根的运算叫做开平方
     *
     * 0的算数平方根为0
     *
     * 根据上述的平方根的概念，假设最终x被开平方后的结果为k，可以得到下面的公式：
     *
     * k2 = x -> k = 根号x
     *
     * 求出k的平方 <= x 模拟求根号x，二分法找到最大的那个k值
     *
     * T:O(logn)
     * S:O(1)
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        int left = 0;
        int right = x;
        int k = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) { // 模拟 k2 = x
                left = mid + 1;
                k = mid;
            } else {
                right = mid - 1;
            }
        }
        return k;
    }
}
