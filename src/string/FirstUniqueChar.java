package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符：
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 */
public class FirstUniqueChar {

    public static void main(String[] args) {
        FirstUniqueChar main = new FirstUniqueChar();
        System.out.println(main.firstUniqueChar("leecode"));
    }

    /**
     * 用Map存储每个字符在字符串出现的次数，再遍历字符串找到只出现一次的字符则是要找到的结果
     *
     * T:O(n)
     * S:O(n)
     */
    private int firstUniqueChar(String s) {
        if (s == null || s.length() == 0) return -1;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            Integer count = map.get(c);
//            map.put(c, count == null ? 1 : count + 1);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
}
