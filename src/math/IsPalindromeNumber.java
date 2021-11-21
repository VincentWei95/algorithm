package math;

/**
 * 9.回文数：
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 *
 * 示例2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 示例 4：
 *
 * 输入：x = -101
 * 输出：false
 */
public class IsPalindromeNumber {

    public static void main(String[] args) {
        IsPalindromeNumber main = new IsPalindromeNumber();
        System.out.println(main.isPalindromeNum(12321));
    }

    /**
     * 方式1：用判断回文字符串的方式就可以实现，要把数字转成字符串来对比
     *
     * T:O(m) m表示整数的位数长度
     * S:O(1)
     */
    private boolean isPalindromeNumber(int num) {
        String numStr = String.valueOf(num);
        int left = 0;
        int right = numStr.length() - 1;
        for (; left < right; left++, right--) {
            if (numStr.charAt(left) == numStr.charAt(right)) continue;
            return false;
        }
        return true;
    }

    /**
     * 方式2：限定不能将整数转为字符串来判断回文数字
     *
     * T:O(m)
     * S:O(1)
     */
    private boolean isPalindromeNum(int x) {
        if (x < 0) return false;
        int temp = x;
        long y = 0;
        // 假设数字 123
        // 1:
        // num = 123 % 10 = 3, y = 0 * 10 + 3 = 3, temp = 123 / 10 = 12
        // 2:
        // num = 12 % 10 = 2, y = 3 * 10 + 2 = 32, temp = 12 / 10 = 1
        // 3:
        // num = 1 % 10 = 1, y = 32 * 10 + 1 = 321,temp = 1 / 10 = 0
        while (temp != 0) {
            int num = temp % 10; // 每次拿去x第n位数递减的余数
            y = y * 10 + num; // y是对比的最终值
            temp = temp / 10; // 把值减少一位后继续循环
        }
        return y == x; // 如果相等说明是回文数字
    }
}
