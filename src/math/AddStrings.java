package math;

/**
 * 415.字符串相加：
 *
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
 *
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 */
public class AddStrings {

    public static void main(String[] args) {
        AddStrings main = new AddStrings();
        System.out.println(main.addStrings("100", "2000"));
    }

    /**
     * 设定i和j两个指针分别指向num1和num2尾部，模拟人工加法：
     *
     * 计算进位：计算carry=tmp / 10，代表当前位相加是否产生进位
     * 添加当前位：计算tmp = n1 + n2 + carry，并将当前为tmp % 10添加到StringBuilder
     * 索引溢出处理：当指针i或j走过数字首部后，给n1、n2赋值为0，相当于给num1、num2中长度较短的数字前面填0，以便后续计算
     * 当遍历完num1、num2后跳出循环，并根据carry值决定是否在头部添加进位1
     *
     * T:O(max(m+n)) m和n为2数字长度，按位遍历一遍数字（以较长的数字为准）
     * S:O(1)
     */
    private String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // 进位，上一次计算有进位carry=1，否则carry=0
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 两个字符串当前位的数值相加并添加上一次计算的进位
            int tmp = n1 + n2 + carry;
            // 计算是否有进位
            carry = tmp / 10;
            // 计算结果
            sb.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) sb.append(1);
        return sb.reverse().toString();
    }
}
