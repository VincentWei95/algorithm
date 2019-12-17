package easy;

/**
 * 回文数字：
 *
 * 给你一个整数，你要判断它是否是一个回文数字。所谓回文数字就是，你正着读和反着读都是同一个数字。
 *
 * 比如，给你的数字是：
 *
 * 12321
 *
 * 无论你从左向右读，还是从右向左读，都是 12321，所以它是一个回文数字，你要返回 true。
 *
 * 再比如说：
 *
 * -232
 *
 * 你从左向右读是 -232，但从右向左读则是 232-，和 -232 不一样，因此它不是一个回文数字，你要返回 false。
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber main = new PalindromeNumber();
        System.out.println(main.isPalindromeNum(12321));
    }

    /**
     * 用判断回文字符串的方式就可以实现，要把数字转成字符串来对比
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
     * 限定不能将整数转为字符串来判断回文数字
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
