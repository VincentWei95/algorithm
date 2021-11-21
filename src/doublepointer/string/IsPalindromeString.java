package doublepointer.string;

/**
 * 125.验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * 提示：
 *
 * 字符串 s 由 ASCII 字符组成
 */
public class IsPalindromeString {

    public static void main(String[] args) {
        IsPalindromeString palindrome = new IsPalindromeString();
        System.out.println(palindrome.isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        int left = 0;
        int right = s.length() - 1;
        for (; left < right; left++, right--) {

            // 左边字符如果不是字母或数字则跳过指向下一个
            while (left < right && !isAlphanumeric(s.charAt(left))) left++;
            // 右边字符如果不是字母或数字则跳过指向上一个
            while (left < right && !isAlphanumeric(s.charAt(right))) right--;

            if (left < right && !isEqualsIgnoreCase(s.charAt(left), s.charAt(right))) return false;
        }
        return true;
    }

    /**
     * 字符是否为字母或数字
     */
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    /**
     * 两个字符忽略大小写是否相等
     *
     * 小写字母比大写字母大32，这里+32是转换为小写字母
     */
    private boolean isEqualsIgnoreCase(char left, char right) {
        if (left >= 'A' && left <= 'Z') left += 32;
        if (right >= 'A' && right <= 'Z') right += 32;
        return left == right;
    }
}
