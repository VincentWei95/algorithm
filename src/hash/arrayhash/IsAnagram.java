package hash.arrayhash;

/**
 * 242.有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 *
 * 示例1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 注意：s 和 t 仅包含小写字母
 */
public class IsAnagram {

    /**
     * 用数组作为 map，先存字符串 t 的每个字符出现的次数+1，遍历字符串 s 时出现的字符串都-1，最终遍历 map 如果都是0则有效
     *
     * T:O(n)
     * S:O(n)
     */
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        char[] tCharArray = t.toCharArray();
        for (char c : tCharArray) {
            map[c - 'a'] += 1;
        }

        char[] sCharArray = s.toCharArray();
        for (char c : sCharArray) {
            map[c - 'a'] -= 1;
        }

        for (int num : map) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
