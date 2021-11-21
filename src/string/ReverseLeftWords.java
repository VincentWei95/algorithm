package string;

/**
 * 左旋转字符串
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 *
 * 输入: s = "abcdefg", k = 2
 * 输出:"cdefgab"
 *
 * 示例 2：
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出:"umghlrlose"
 */
public class ReverseLeftWords {

    /**
     * 如果使用str.substring()这道题目会非常简单一句代码就可以解决
     * 现在要求自己写一个方法实现同样的功能，可以按如下步骤：
     *
     * 1、先反转前n个字符
     * 2、反转n到str.length长度的字符
     * 3、反转整个字符串
     *
     * 1、
     * abcdefg -> bacdefg
     *
     * 2、
     * bacdefg -> bagfedc
     *
     * 3、
     * bagfedc -> cdefgab
     */
    public String reverseLeftWords(String s, int n) {
        // return s.substring(n, s.length()) + s.substring(0, n);
        char[] chars = s.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}
