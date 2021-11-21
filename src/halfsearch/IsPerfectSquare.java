package halfsearch;

/**
 * 367.有效的完全平方数
 *
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 */
public class IsPerfectSquare {

    /**
     * 完全平方数即 k2 = num 的数值，可以使用二分法查找这个数值
     *
     * T:O(logn)
     * S:O(1)
     */
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;

        long left = 2;
        long right = num / 2;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long k = mid * mid;
            if (k == num) return true; // 找到完全平方数

            if (k > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
