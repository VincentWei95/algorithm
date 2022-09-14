package string;

/**
 * 459. 重复的子字符串
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 *
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class RepeatedSubstringPattern {

    /**
     * 该题目同样是经典的kmp题型，从一个字符串中找是否有子串符合
     *
     * 获取到前缀表 next 后，判断是否有重复可构成字符串的子串，需要满足条件（假设字符串长度为 n)：
     * 1、前缀表 next 最后一个元素 != -1
     * 说明字符串有最长相同的前后缀（就是字符串里的前缀子串和后缀子串相同的最长长度），最长相等前后缀长度 next[n - 1] + 1
     *
     * 2、n % (n - (next[n - 1] + 1) == 0，可以被整除说明该字符串有重复的子串
     * n - (next[n - 1] + 1)【字符串长度 - 最长相同前后缀的长度】，相当于是一个周期的长度，如果这个周期可以被整除就是这个周期的循环
     *
     * 字符串：a  s  d  f  a  s  d  f  a  s  d  f
     * next：-1 -1 -1 -1  0  1  2  3  4  5  6  7
     *
     * next[n - 1] = 7, next[n - 1] + 1 = 8, 8 就是此时字符串 asdfasdfasdf 的最长相同前后缀的长度
     * n - (next[n-1] + 1)：12【字符串的长度】- 8【最长公共前后缀的长度】= 4，4 正好可以被 12（字符串的长度）整除，所以说明有重复的子字符串（asdf）
     *
     * T:O(n)
     * S:O(n)
     */
    // 前缀表不减1
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] next = new int[n];
        int j = -1;
        next[0] = j;
        for (int i = 1; i < n; i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
        if (next[n - 1] != -1 && n % (n - (next[n - 1] + 1)) == 0) {
            return true;
        }
        return false;
    }

    // 前缀表减1
    public boolean repeatedSubstringPattern1(String s) {
        int n = s.length();
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        if (next[n - 1] != 0 && n % (n - next[n - 1]) == 0) {
            return true;
        }
        return false;
    }
}
