package hash.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 387.字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class FirstUniqChar {

    /**
     * T:O(n)
     * S:O(l)，map存储26个小写字母，l <= 26
     */
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;

        Map<Character, Integer> map = new HashMap<>(26);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) return i;
        }
        return -1;
    }
}
