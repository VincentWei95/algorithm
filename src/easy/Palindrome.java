package easy;

/**
 * 回文字符串：只考虑字母和数字，左边和右边字符往中间靠拢对比都是相同的，则认为是回文字符串
 *
 * race a E-car就是回文字符串：左边的r和右边的r对比相同，继续往中间靠拢，左边的a和右边的a也是相同，依次类推
 *
 * race a car不是回文字符串
 *
 * 算法实现：
 *
 * 采用双指针，左右指针往中间靠拢每个进行对比
 *
 * 时间复杂度和空间复杂度：
 * T:O(n) 最多会循环一个字符串长度
 * S:O(1)
 */
public class Palindrome {

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
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
