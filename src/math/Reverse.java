package math;

/**
 * 7.整数反转：
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 */
public class Reverse {

    public static void main(String[] args) {
        Long t = 99888882L;
        int n = Math.toIntExact(t);
        Reverse main = new Reverse();
        System.out.println(main.reverseInt(-321));
    }

    /**
     * 没有辅助栈/数组的帮助下弹出和推入数字，可以使用数学方法：
     *
     * // pop operation
     * pop = x % 10;
     * x /= 10;
     *
     * // push operation
     * tmp = rev * 10 + pop;
     * rev = tmp;
     *
     * 但当tmp = rev * 10 + pop是有可能会导致溢出
     *
     * 幸运的是，事先检查这个语句是否会溢出很容易
     *
     * 假设rev是正数
     *
     * ①如果 tmp= rev * 10 + pop 导致溢出，那么一定有 rev >= Integer.MAX_VALUE / 10
     * ②如果 rev >= Integer.MAX_VALUE / 10，那么 tmp = rev * 10 + pop一定会溢出
     * ③如果 rev == Integer.MAX_VALUE / 10，那么只要 pop > 7，tmp = rev * 10 + pop 就会溢出
     */
    private int reverseInt(int x) {
        int rev = 0;
        while (x != 0) {
            // pop operation
            int pop = x % 10;
            x /= 10;
            // 超过Integer.MAX_VALUE
            if (rev > Integer.MAX_VALUE / 10) return 0;
            // 小于 Integer.MIN_VALUE
            if (rev < Integer.MIN_VALUE / 10) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    // 个人实现
    private int reverse(int x) {
        boolean isLowThanZero = false;
        if (x < 0) {
            isLowThanZero = true;
            x = Math.abs(x);
        }

        // 转成字符串反转
        String str = String.valueOf(x);
        StringBuilder sb = new StringBuilder(str);
        str = sb.reverse().toString();

        // 有可能str已经在[Integer.MIN_VALUE, Integer.MAX_VALUE]之外，所以用更大的Long转换避免溢出
        long result = Long.valueOf(str);
        // 如果是负数
        if (isLowThanZero) {
            result = -result;
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result;
    }
}
