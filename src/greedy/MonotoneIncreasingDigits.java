package greedy;

/**
 * 738.单调递增数字
 *
 * 给定一个非负整数N，找出小于或等于N的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字x和y满足x <= y时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 *
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 *
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 */
public class MonotoneIncreasingDigits {

    /**
     * 思路：
     * 假设要处理的数字是98，当出现 strNum[i-1] > strNum[i] 的情况（非单调递增），即第一位数字 9 > 第二位数字 8
     * 首先先让 strNum[i-1]--，然后strNum[i]赋值为9，这样这个整数就是89，即小于98的最大的单调递增整数
     *
     * 局部最优：遇到 strNum[i-1] > strNum[i] 的情况，让 strNum[i-1]--，strNum[i] 赋值为9，可以保证这两位变成最大单调递增整数
     * 全局最优：得到小于等于N的最大单调递增整数
     *
     * 上面的局部最优推出全局最优，还需要其他条件，即遍历顺序，和标记从哪一位开始统一改为9
     * 此时是从前向后遍历还是从后向前遍历呢？
     * 假设从前向后遍历，N是332，当 strNum[i-1] > strNum[i]，即第二位数字 3 > 第三位数字 2
     * 此时 strNum[i-1]-- = 3 - 1 = 2，strNum[i]赋值为9，结果是 329，此时 2又小于第一位数字 3，因为是从前向后遍历的，此时不能要再去判断 strNum[i-2] 就变复杂了。真正的结果应该是 299
     *
     * 所以从前向后遍历会改变已经遍历过的结果。如果从后向前遍历，就可以重复利用上次比较得出的结果了。
     * 从后向前遍历数字 332 变化为：332 -> 329 -> 299
     *
     * T:O(n)
     * S:O(n)
     */
    public int monotoneIncreasingDigits(int n) {
        if (n == 0) return 0;

        char[] chars = String.valueOf(n).toCharArray();
        int start = Integer.MAX_VALUE; // 设置最大值，防止当数字本身是单调递增时，没有一位数字需要改成9的情况
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--; // 上一位-1
                start = i; // 记录要赋值为9的位置
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == '0') continue; // 避免出现 09 这样的情况

            if (i >= start) {
                sb.append('9');
            } else {
                sb.append(chars[i]);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
