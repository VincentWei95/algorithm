package hash.arrayhash;

/**
 * 383.赎金信
 *
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 *
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *
 * 提示：
 *
 * 你可以假设两个字符串均只含有小写字母。
 */
public class CanConstruct {

    /**
     * T:O(n)
     * S:O(n)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];
        for (char c : magazine.toCharArray()) {
            map[c - 'a'] += 1;
        }
        for (char c : ransomNote.toCharArray()) {
            map[c - 'a']--;
            // 如果没有说明杂志已经没有能找到的字符了，直接返回失败
            if (map[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
