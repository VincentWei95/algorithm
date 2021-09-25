package math;

/**
 * 爬楼梯方法数：
 *
 * 给你一个 n 阶的楼梯，每次你可以爬 1 阶或 2 阶，你要求出爬完这个楼梯有多少种不同的方法
 *
 * 比如说，楼梯只有 1 阶，显然你只有一种爬法，就是爬 1 阶，然后到顶
 *
 * 再比如说，楼梯有 2 阶，那么你可以用两次 1 阶爬到顶，也可以用一次 2 阶爬到顶。共 2 种爬法
 */
public class ClimbStairs {

    public static void main(String[] args) {

    }

    /**
     * 分析：
     *
     * n     f
     * 0     1
     * 1     1
     * 2     2
     *
     * 当楼梯n有0阶时，只有一种爬楼梯的方式；
     * 当楼梯n有1阶时，只有一种爬楼梯的方式；
     * 当楼梯n有2阶时，有两种爬楼梯的方式：一步一阶，两步两阶
     *
     * 实质上这道题是要求解斐波那契：
     *
     * f(n) = f(n-1) + f(n-2)
     * 或
     * f(n) = 1, n < 2
     */

    /**
     * 方式1：递归实现（仅作参考，时间复杂度太大）
     *
     * T:O(2的n次方）
     */
    private int climbStairsRecursive(int n) {
        if (n < 2) return 1;
        return climbStairsRecursive(n-1) + climbStairsRecursive(n-2);
    }

    /**
     * 方式2：循环实现
     *
     * f(x) = f(x-1) + f(x-2) 表示第 x 级台阶是 x-1 和 x-2 台阶方案的和
     *
     * 爬第0级台阶：f(0)=1 只有一种方案
     * 爬第1级台阶：f(1)=1 只有一种方案
     * 这两个可以作为边界条件
     *
     * f(2)= f(1) + f(0) = 1 + 1 = 2
     * f(3)= f(2) + f(1) = 2 + 1 = 3
     * f(4)= f(3) + f(2) = 2 + 3 = 5
     *
     * T:O(n)
     * S:O(1)
     */
    private int climbStairsInterative(int n) {
        int first = 1; // f(0)
        int second = 1; // f(1)
        for (int i = 1; i < n; i++) {
            // 不断将前两项之和相加得到第三项
            int third = first + second; // f(2) = f(0) + f(1)
            // 更新前两项的值
            first = second; // first = f(1)
            second = third; // second = f(2)
        }

        // 最后第三项的值给了second，所以返回second
        return second;
    }
}
