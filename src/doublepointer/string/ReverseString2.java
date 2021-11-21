package doublepointer.string;

/**
 * 541.反转字符串2
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 1、如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 2、如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 * 示例 2：
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 */
public class ReverseString2 {

    /**
     * 该题目有两个条件，可以概括为：每间隔2k个反转前k个，尾数不够k个的时候全部反转
     *
     * T:O(n)
     * S:O(1)
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i += 2 * k) {
            int start = i;
            int end = Math.min(n - 1, start + k - 1); // 判断尾数够不够k个来取决end指针的位置
            while (start < end) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
                start++;
                end--;
            }
        }
        return new String(chars);
    }
}
