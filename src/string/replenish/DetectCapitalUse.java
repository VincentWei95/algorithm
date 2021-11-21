package string.replenish;

/**
 * 520.检测大写字母：
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写，比如"Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入：word = "USA"
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：word = "FlaG"
 * 输出：false
 *
 * 提示：
 *
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 */
public class DetectCapitalUse {

    public static void main(String[] args) {
        DetectCapitalUse main = new DetectCapitalUse();
        System.out.println(main.detectCapitalUse("FlaG"));
    }

    // 判断如果第一个字符是大写，如果第二个字符也是大写，说明往下字符都要大写，否则false
    // 判断如果第一个字符是大写，如果第二个字符是小写，说明往下字符都要小写，否则false
    // 判断如果第一个字符是小写，说明往下字符都要小写，否则false
    private boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) ||
                word.equals(word.toLowerCase()) ||
                word.equals(Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase());
    }
}
