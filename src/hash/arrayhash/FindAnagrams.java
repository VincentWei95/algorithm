package hash.arrayhash;

import java.util.ArrayList;
import java.util.List;

/**
 * 438.找到字符串中所有字母异位词
 *
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 *
 * s和p仅包含小写字母
 */
public class FindAnagrams {

    /**
     * 滑动窗口+双指针
     *
     * s = "cbaebabacd", p = "abc"
     *
     * 一、将 p 字符串的字符存入数组 pMap
     *
     * pMap['a'] = 1
     * pMap['b'] = 1
     * pMap['c'] = 1
     *
     * 二、
     * s 字符串同样创建数组 sMap 存储走过的字符，和 pMap 对比
     * 如果 sMap 的字符串出现次数 == pMap，说明字符在 pMap 存在，right 指针继续走，right - left + 1 记录字符，当与 p 字符串长度相同时，说明[left, right]的字符为异位词
     * 如果 sMap 的字符出现次数 > pMap，说明 pMap没有该字符，left 指针往前走，直到条件不成立，right 指针继续走
     *
     * 1、
     *  c   b   a   e   b   a   b   a   c   d
     * left
     * right
     *
     * left = 0, right = 0, pLength = 3
     * sMap['c'] = 1，sMap['c'] == pMap['c']，right - left + 1 < pLength, right++
     *
     * 2、
     *  c   b   a   e   b   a   b   a   c   d
     * left
     *    right
     *
     * left = 0, right = 1, pLength = 3
     * sMap['b'] = 1, sMap['b'] == pMap['b'], right - left + 1 < pLength, right++
     *
     * 3、
     *  c   b   a   e   b   a   b   a   c   d
     * left
     *        right
     *
     * left = 0, right = 2, pLength = 3
     * sMap['a'] = 1, sMap['b'] == pMap['b'], right - left + 1 == pLength, right++，记录left=0
     *
     * 4、
     *  c   b   a   e   b   a   b   a   c   d
     * left
     *            right
     *
     * left = 0, right = 3, pLength = 3
     * sMap['e'] = 1, sMap['e'] > pMap['e'], sMap['c']--, left++
     * sMap['e'] = 1, sMap['e'] > pMap['e'], sMap['b']--, left++
     * sMap['e'] = 1, sMap['e'] > pMap['e'], sMap['a']--, left++
     * sMap['e'] = 1, sMap['e'] == pMap['e'], right - left + 1 < pLength, right++
     *
     * 5、
     *  c   b   a   e   b   a   b   a   c   d
     *             left
     *                right
     *
     * left = 3, right = 4, pLength = 3
     * sMap['b'] = 1, sMap['b'] == pMap['b'], right - left + 1 < pLength, right++
     *
     * 6、
     *  c   b   a   e   b   a   b   a   c   d
     *             left
     *                    right
     *
     * left = 3, right = 5, pLength = 3
     * sMap['a'] = 1, sMap['a'] == pMap['a'], right - left + 1 < pLength, right++
     *
     * 7、
     *  c   b   a   e   b   a   b   a   c   d
     *             left
     *                        right
     *
     * left = 3, right = 6, pLength = 3
     * sMap['b'] = 2, sMap['b'] > pMap['b'], sMap['e']--, left++
     * sMap['b'] = 2, sMap['b'] > pMap['b'], sMap['b']--, left++
     * sMap['b'] = 1, sMap['b'] == pMap['b'], right - left + 1 < pLength, right++
     *
     * 8、
     *  c   b   a   e   b   a   b   a   c   d
     *                        left
     *                           right
     *
     * left = 6, right = 7, pLength = 3
     * sMap['a'] = 1, sMap['a'] == pMap['a'], right - left + 1 < pLength, right++
     *
     * 9、
     *  c   b   a   e   b   a   b   a   c   d
     *                        left
     *                               right
     *
     * left = 6, right = 8, pLength = 3
     * sMap['c'] = 1, sMap['c'] == pMap['c'], right - left + 1 == pLength, right++，记录left=6
     *
     *  c   b   a   e   b   a   b   a   c   d
     *                        left
     *                                    right
     *
     * left = 6, right = 9, pLength = 3
     * sMap['d'] = 1, sMap['d'] > pMap['d'], sMap['b']--, left++
     * sMap['d'] = 1, sMap['d'] > pMap['d'], sMap['a']--, left++
     * sMap['d'] = 1, sMap['d'] > pMap['d'], sMap['c']--, left++
     * sMap['d'] = 1, sMap['d'] == pMap['d'], right - left + 1 < pLength
     *
     * 最终结果为[0, 6]
     *
     * T:O(n)
     * S:O(1)
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 存储字符串p每个字符出现的次数
        int[] pMap = new int[26];
        for (char c : p.toCharArray()) {
            pMap[c - 'a']++;
        }

        List<Integer> result = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        int[] sMap = new int[26];
        int left = 0;
        for (int right = 0; right < n; right++) {
            int curRight = s.charAt(right) - 'a';
            sMap[curRight]++; // 遍历字符串s时同样提供一个map记录每个字符出现的次数
            // 如果字符在 pMap 不存在，left指针往前走，直到不满足条件
            while (sMap[curRight] > pMap[curRight]) {
                int curLeft = s.charAt(left) - 'a';
                sMap[curLeft]--;
                left++;
            }
            // 两个指针的距离和字符串p长度相同，说明是异位词，记录异位词开始索引
            if (right - left + 1 == m) {
                result.add(left);
            }
        }
        return result;
    }
}
