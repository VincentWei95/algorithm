package hash.arrayhash;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002.查找共用字符
 *
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 *
 * 输入：words = ["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 *
 * words[i] 由小写英文字母组成
 */
public class CommonChars {

    /**
     * 1、将第一个字符串作为参照记录字符串每个字符出现的次数
     * 2、遍历字符串数组，每个字符串同样提供 map 存储字符出现的次数，循环26个小写字母字符对比记录同个字符出现最少的次数
     * 3、获取最终记录的字符，要注意包含重复字符
     *
     *          a  b  c  d  e .... l ... o  p  q  r ...
     * "bella"  1  1  0  0  1      2     0  0  0  0
     * "label"  1  1  0  0  1      2     0  0  0  0
     * "roller" 0  0  0  0  1      2     1  0  0  1
     *  result  0  0  0  0  1      2     0  0  0  0
     *
     * result=["e", "l", "l"]
     *
     * T:O(n(m+k))：n是字符串数组的长度，m是每个字符串的平均长度，k是26
     * S:O(n)：存储字符出现次数map数组
     */
    public List<String> commonChars(String[] words) {
        // 以第一个字符串作为参照
        int[] map = new int[26];
        for (char c : words[0].toCharArray()) {
            map[c - 'a']++;
        }

        for (String word : words) {
            // 同样记录每个字符串字符的出现次数
            int[] strMap = new int[26];
            for (char c : word.toCharArray()) {
                strMap[c - 'a']++;
            }

            // 获取相同字符出现次数最小的
            for (int j = 0; j < 26; j++) {
                map[j] = Math.min(map[j], strMap[j]);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            // 使用 while 循环是需要考虑重复字符情况
            while (map[i] != 0) {
                char c = (char) (i + 'a');
                result.add(String.valueOf(c));
                map[i]--;
            }
        }
        return result;
    }
}
