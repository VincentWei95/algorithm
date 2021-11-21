package math;

import java.util.HashMap;
import java.util.Map;

/**
 * 8.字符串转换整数
 *
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数myAtoi(string s) 的算法如下：
 *
 * 1、读入字符串并丢弃无用的前导空格
 * 2、检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 3、读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 4、将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 5、如果整数数超过 32 位有符号整数范围 [−231, 231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231− 1 。
 * 返回整数作为最终结果。
 *
 * 注意：
 *
 * 1、本题中的空白字符只包括空格字符 ' ' 。
 * 2、除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * 示例1：
 *
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："42"（读入 "42"）
 *            ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 *
 * 示例2：
 *
 * 输入：s = "   -42"
 * 输出：-42
 * 解释：
 * 第 1 步："   -42"（读入前导空格，但忽视掉）
 *             ^
 * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 *              ^
 * 第 3 步："   -42"（读入 "42"）
 *                ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
 *
 * 示例3：
 *
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 *              ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
 *
 * 示例4：
 *
 * 输入：s = "words and 987"
 * 输出：0
 * 解释：
 * 第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
 *          ^
 * 解析得到整数 0 ，因为没有读入任何数字。
 * 由于 0 在范围 [-231, 231 - 1] 内，最终结果为 0 。
 *
 * 示例5：
 *
 * 输入：s = "-91283472332"
 * 输出：-2147483648
 * 解释：
 * 第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
 *           ^
 * 第 3 步："-91283472332"（读入 "91283472332"）
 *                      ^
 * 解析得到整数 -91283472332 。
 * 由于 -91283472332 小于范围 [-231, 231 - 1] 的下界，最终结果被截断为 -231 = -2147483648 。
 *
 * 提示：
 *
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class MyAtoi {

    /**
     * 该题目使用状态机实现
     *
     * 每个状态如下：
     * start：开始状态，此时没有记录任何数字，除了遇到 ' '、'+'/'-' 和数字外，其他情况都会停止读取
     * signed：遇到 '+'/'-' 符号，此时除了遇到数字，其他情况都会停止读取
     * number：遇到数字，其他情况都会停止读取
     * end：结束状态，停止读取
     *
     * 表格如下：
     *
     * | state  | ' '   | '+'/'-' | '0'-'9' | other |
     * | start  | start | signed  | number  | end   |
     * | signed | end   | signed  | number  | end   |
     * | number | end   | end     | number  | end   |
     * | end    | end   | end     | end     | end   |
     *
     * 根据上述表格创建状态机，遍历字符串的每个字符，计算结果
     *
     * T:O(n)
     * S:O(1)
     */
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    private static final class Automaton {
        static final String STATE_START = "start";
        static final String STATE_SIGNED = "signed";
        static final String STATE_NUMBER = "number";
        static final String STATE_END = "end";
        int sign = 1; // 1表示整数，-1表示负数
        String state = STATE_START; // 当前状态
        long ans = 0; // 最终结果
        final Map<String, String[]> table = new HashMap<String, String[]>() {{
            put(STATE_START, new String[]{STATE_START, STATE_SIGNED, STATE_NUMBER, STATE_END});
            put(STATE_SIGNED, new String[] {STATE_END, STATE_END, STATE_NUMBER, STATE_END});
            put(STATE_NUMBER, new String[] {STATE_END, STATE_END, STATE_NUMBER, STATE_END});
            put(STATE_END, new String[] {STATE_END, STATE_END, STATE_END, STATE_END});
        }};

        public void get(char c) {
            state = table.get(state)[getState(c)];
            if (STATE_NUMBER.equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, - (long) Integer.MIN_VALUE);
            } else if (STATE_SIGNED.equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        // 根据字符的类型获取状态
        int getState(char c) {
            if (c == ' ') return 0;
            if (c == '+' || c == '-') return 1;
            if (Character.isDigit(c)) return 2;
            return 3;
        }
    }
}
