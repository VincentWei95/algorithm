package string.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串：
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

    }

    /**
     * 滑动窗口实现
     *
     * 提供start和end两个指针，start指向不重复字符，end是循环指针
     *
     * 当end指向的字符不在map中则存储，在end指向的字符在map中，start移动到当前字符位置
     *
     * 步骤1：
     * [p, w, w, k, e, w]
     * s
     * e
     * start = 0, end = 0, result = 1, map[p, 1], start = 0, end = 1
     * -----------------------------------------------------------------
     *
     * 步骤2：
     * [p, w, w, k, e, w]
     * s
     *     e
     * start = 0, end = 1, result = 1, map[w, 2], start = 0, end = 2
     * -----------------------------------------------------------------
     *
     * 步骤3：
     * [p, w, w, k, e, w]
     * s
     *        e
     * map中找到字符，start移动位置
     * [p, w, w, k, e, w]
     *        s
     *        e
     * start = 2, end = 2, result = 1, map[w, 3], start = 2, end = 3
     * -----------------------------------------------------------------
     *
     * 步骤4：
     * [p, w, w, k, e, w]
     *        s
     *           e
     * start = 2, end = 3, result = 2, map[k, 4], start = 2, end = 4
     * -----------------------------------------------------------------
     *
     * 步骤5：
     * [p, w, w, k, e, w]
     *        s
     *              e
     * start = 2, end = 4, result = 3, map[e, 5], start = 2, end = 5
     * -----------------------------------------------------------------
     *
     * 步骤6：
     * [p, w, w, k, e, w]
     *        s
     *                 e
     * map中找到字符，start移动位置
     * [p, w, w, k, e, w]
     *                 s
     *                 e
     * start = 5, end = 5, result = 3, map[w, 6], start = 5, end = 6
     * -----------------------------------------------------------------
     *
     * T:O(n)
     * S:(n)
     */
    private int lengthOfLongestSubstring(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            }
            result = Math.max(result, end - start + 1);
            map.put(c, end + 1);
        }
        return result;
    }
}
