package string;

/**
 * 检测大写字母：
 *
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 输入: "USA"
 * 输出: true
 *
 * 输入: "FlaG"
 * 输出: false
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
