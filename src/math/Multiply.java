package math;

/**
 * 43.字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * 1、num1和num2的长度小于110。
 * 2、num1 和num2 只包含数字0-9。
 * 3、num1 和num2均不以零开头，除非是数字 0 本身。
 * 4、不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Multiply {

    /**
     * 做加法
     *
     * 和字符串相加算法相似，将每个字符做乘积后的字符串反转
     *
     * num1 = 1234，num2 = 567
     *
     *       1234           1234        1234        1234
     *      x 567          x   7       x  6        x 5
     * -------------    ---------  + --------- + ---------
     *      8638     =>     8638       74040      617000
     *     74040
     *    617000
     * -------------
     *    699678
     *
     * T:O(mn + n2)：m和n分别是num1和num2的长度
     * 需要从右往左遍历num2，对于num2的每一位，都需要和num1的每一位计算乘积
     * 因此计算乘积的总次数是mn
     * 字符串相加操作共有n次，相加的字符串长度最长为m+n，因此字符串相加的时间是O(mn+n2)
     * 总时间复杂度是O(mn+n2)
     *
     * S:O(m+n)：m和n分别是num1和num2的长度
     * 空间复杂度取决于存储中间状态的字符串，由于乘积的最大长度为m+n
     * 因此存储中间状态的字符串的长度不会超过m+n
     */
    public String multiply(String num1, String num2) {
        // 0 相乘任何数值都为0，直接返回
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length();
        int n = num2.length();
        String ans = "0";
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            // 每乘积一位，后面都要补0
            for (int j = n - 1; j > i; j--) {
                sb.append("0");
            }
            int carry = 0; // 进位
            int y = num2.charAt(i) - '0'; // 获取数值，依次为7、6、5
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int z = x * y + carry; // 计算每一位的乘积
                sb.append(z % 10); // 截取余数
                carry = z / 10; // 进位轮到下一个位累加
            }
            if (carry != 0) {
                sb.append(carry);
            }
            ans = addStrings(ans, sb.reverse().toString()); // 和上一个乘积结果相加，字符串相加算法
        }
        return ans;
    }

    private String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int z = x + y + carry;
            sb.append(z % 10);
            carry = z / 10;
            i--;
            j--;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
