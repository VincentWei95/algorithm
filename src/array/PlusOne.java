package array;

/**
 * 加一：
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {

    public static void main(String[] args) {

    }

    private int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            // 加1后整除10为0，说明有进位要前面要继续+1，否则返回
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 还有进位，要前面补充
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
