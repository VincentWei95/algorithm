package doublepointer.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 3.无重复字符的最长子串：
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
     * start = 0, end = 0, result = 0, map[p, 0]; start = 0, end = 1
     * -----------------------------------------------------------------
     *
     * 步骤2：
     * [p, w, w, k, e, w]
     * s
     *     e
     * start = 0, end = 1, result = 0, map[w, 1]; start = 0, end = 2
     * -----------------------------------------------------------------
     *
     * 步骤3：
     * [p, w, w, k, e, w]
     * s
     *        e
     * map中找到字符w有重复(map存有索引为1的w），start移动位置到map中重复字符的位置+1，即map中重复字符w的索引1+1=2，更新map重复字符w的索引为2
     * [p, w, w, k, e, w]
     *        s
     *        e
     * start = 0, end = 2, result = 0, map[w, 2]; start = 2, end = 3, result = end - start + 1 = 3 - 2 + 1 = 2
     * -----------------------------------------------------------------
     *
     * 步骤4：
     * [p, w, w, k, e, w]
     *        s
     *           e
     * start = 2, end = 3, result = 2, map[k, 3]; start = 2, end = 4
     * -----------------------------------------------------------------
     *
     * 步骤5：
     * [p, w, w, k, e, w]
     *        s
     *              e
     * start = 2, end = 4, result = 2, map[e, 4]; start = 2, end = 5
     * -----------------------------------------------------------------
     *
     * 步骤6：
     * [p, w, w, k, e, w]
     *        s
     *                 e
     * map中找到字符w有重复(map存有索引为2的w），start移动位置到map中重复字符的位置+1，即map中重复字符w的索引2+1=3，更新map重复字符w的索引为5
     * [p, w, w, k, e, w]
     *           s
     *                 e
     * start = 2, end = 5, result = 2, map[w, 5]; start = 3, end = 5, result = end - start + 1 = 5 - 3 + 1 = 3
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
                // 因为 start 指针要指向不重复的字符，遇到字符相同时，需要跳过当前end指向的重复字符
                // start 移动到重复字符的下一位，所以 + 1
                start = Math.max(start, map.get(c) + 1);
            }
            result = Math.max(result, end - start + 1); // end和start都是索引，因为是计算长度所以要+1
            map.put(c, end);
        }
        return result;
    }
}
