package hash.hashset;

import java.util.HashSet;
import java.util.Set;

/**
 * 202.快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 *
 * 示例 1：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 */
public class IsHappy {

    /**
     * 题目中提示如果不是快乐数会出现无限循环，也就是说求和sum会重复出现，这也是解题的关键
     * 当出现重复的sum时，说明不是快乐数
     *
     * T:O(logn)
     * S:O(logn)
     */
    public boolean isHappySet(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * 双指针法
     *
     * T:O(logn)
     * S:O(1)
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && fast != slow) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return fast == 1;
    }

    private int getNext(int n) {
        // d = 19 % 10 = 9
        // sum = 0 + 9 * 9 = 81
        // n = 19 / 10 = 1

        // d = 1 % 10 = 1
        // sum = 81 + 1 * 1 = 82
        // n = 1 / 10 = 0
        int sum = 0;
        while (n > 0) {
            int d = n % 10; // 取余数，如果 n=19，即拿的 9
            sum += d * d;
            n = n / 10; // 取整数，如果 n=19，即拿的 1
        }
        return sum;
    }
}
