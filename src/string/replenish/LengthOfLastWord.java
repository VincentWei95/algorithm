package string.replenish;

/**
 * 58.最后一个单词长度：
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 *
 * 示例 2：
 *
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 *
 * 示例 3：
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 * 提示：
 *
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        LengthOfLastWord main = new LengthOfLastWord();
        System.out.println(main.lengthOfLastWord("Hello World"));
    }

    /**
     * 从字符串末尾开始遍历，有两种情况：
     * 1、以字符串"Hello World"为例，从后向前遍历直到遍历到头或者遇到空格为止，即为最后一个单词"World"的长度5
     * 2、以字符串"Hello World"为例，需要先将末尾的空格过滤掉，再进行第一种情况的操作，即认为最后一个单词为"World"，长度为5
     *
     * 所以完整过程为先从后过滤空格找到单词尾部，再从尾部向前遍历，找到单词头部，最后两者相减，即为单词的长度
     *
     * T:O(n) n为结尾空格和结尾单词总体长度
     * S:O(1)
     */
    private int lengthOfLastWord(String str) {
        int end = str.length() - 1;
        while (end >= 0 && str.charAt(end) == ' ') end--;
        if (end < 0) return 0;
        int start = end;
        while (start >= 0 && str.charAt(start) != ' ') start--;
        return end - start;
    }
}
