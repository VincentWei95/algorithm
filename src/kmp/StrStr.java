package kmp;

/**
 *
 * 28.实现strStr
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 *
 * 说明：
 *
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 示例 3：
 *
 * 输入：haystack = "", needle = ""
 * 输出：0

 * 提示：
 *
 * haystack 和 needle 仅由小写英文字符组成
 */
public class StrStr {

    /**
     * 该题目是经典的kmp算法
     *
     * 前缀表会记录字符相同的最长连续子串的长度，当字符不匹配时回退到上一个不连续的字符位置，而不需要重新从头开始匹配
     *
     * 1、
     *     0
     * s = a  a  b  a  a  f
     *        i
     *     j
     *
     * i = 1, j = 0
     * s[i] == s[j], j = j++ = 1, next[i] = next[1] = 1
     *
     *     0  1
     * s = a  a  b  a  a  f
     *        i
     *     j
     *
     * 2、
     *     0  1
     * s = a  a  b  a  a  f
     *           i
     *        j
     *
     * i = 2, j = 1
     * s[i] != s[j], j = next[j - 1] = next[0] = 0
     *
     *     0  1
     * s = a  a  b  a  a  f
     *           i
     *     j
     *
     * i = 2, j = 0
     * s[i] != s[j], next[i] = next[2] = 0
     *
     *     0  1  0
     * s = a  a  b  a  a  f
     *           i
     *     j
     *
     * 3、
     *     0  1  0
     * s = a  a  b  a  a  f
     *              i
     *     j
     *
     * i = 3, j = 0
     * s[i] == s[j], j = j++ = 1, next[i] = next[3] = 1
     *
     *     0  1  0  1
     * s = a  a  b  a  a  f
     *              i
     *        j
     *
     * 4、
     *     0  1  0  1
     * s = a  a  b  a  a  f
     *                 i
     *        j
     *
     * i = 4, j = 1
     * s[i] == s[j], j = j++ = 2, next[i] = next[4] = 2
     *
     *     0  1  0  1  2
     * s = a  a  b  a  a  f
     *                 i
     *           j
     *
     * 5、
     *     0  1  0  1  2
     * s = a  a  b  a  a  f
     *                    i
     *           j
     *
     * i = 5, j = 2
     * s[i] != s[j], j = next[j - 1] = next[1] = 1
     *
     *     0  1  0  1  2
     * s = a  a  b  a  a  f
     *                    i
     *        j
     *
     * i = 5, j = 1
     * s[i] != s[j], j = next[j - 1] = next[0] = 0
     *
     *     0  1  0  1  2  0
     * s = a  a  b  a  a  f
     *                    i
     *     j
     *
     * 最终前缀表为 next[0, 1, 0, 1, 2, 0]
     *
     * T:O(m+n):m 是 haystack 的长度，n 是 needle 的长度
     * S:O(n):n 是 needle 的长度，获取前缀表
     *
     * kmp的理解：https://gitee.com/programmercarl/leetcode-master/blob/master/problems/0028.%E5%AE%9E%E7%8E%B0strStr.md
     */
    // 前缀表不减1实现方式
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        // 先获取前缀表记录
        int[] next = new int[needle.length()];
        for (int i = 1, j = 0; i < needle.length(); i++) {
            // 如果字符不同，回退到上一个不连续的子串位置
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            // 如果字符相同，+1
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j; // 前缀表记录出现的最长相同前缀长度
        }

        for (int i = 0, j = 0; i < haystack.length(); i++) {
            // 对比字符，如果不相同前缀表回退到上一个不连续的子串位置
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }

            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 已经走完模式串的长度，说明找到了返回开始索引
            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }

    // 前缀表减1实现方式
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int[] next = new int[needle.length()];
        next[0] = -1;
        for (int i = 1, j = -1; i < needle.length(); i++) {
            while (j >= 0 && needle.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (needle.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }

        for (int i = 0, j = -1; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return i - needle.length() + 1;
            }
        }
        return -1;
    }
}
