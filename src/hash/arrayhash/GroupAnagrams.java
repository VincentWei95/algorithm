package hash.arrayhash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49.字母异位词分组
 *
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * strs[i]仅包含小写字母
 *
 */
public class GroupAnagrams {

    /**
     * T:O(n(k + q))
     * n 是 strs 中字符串的数量，k 是 strs 中的字符串的最大长度，q 是字符集，在本题中为所有小写字母， q = 26
     * 需要遍历 n 个字符串，对于每个字符，需要O(k)的时间计算每个字母出现的次数，O(q)的时间生成哈希表的键，
     * 以及O(1)的时间更新哈希表，因为总时间复杂度是O(n( k + q))
     *
     * S:O(n(k + q))
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 存储每个字母出现的次数
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }

            // 将字母ASCII表中代表的字符数+字母出现的次数作为key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    // 字母异位词如果相同说明字母出现的次数也相同
                    // 拼接上字母出现的数字，如果能在map找到说明也是在同一组里面的
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
