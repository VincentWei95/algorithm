package string.replenish;

/**
 * 345.反转字符串中的元音字母：
 *
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 *
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * 示例 1：
 *
 * 输入：s = "hello"
 * 输出："holle"
 *
 * 示例 2：
 *
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *
 * 提示：
 *
 * s 由 可打印的 ASCII 字符组成
 */
public class ReverseVowels {

    public static void main(String[] args) {
        ReverseVowels main = new ReverseVowels();
        System.out.println(main.reverseVowels("hello"));
    }

    /**
     * T:O(n)
     * S:O(1)
     */
    private String reverseVowels(String str) {
        int left = 0;
        int right = str.length() - 1;
        char[] arr = str.toCharArray();
        for (; left < right; left++, right--) {
            while (left < right && !isVowels(arr[left])) left++;
            while (left < right && !isVowels(arr[right])) right--;

            if (left < right) {
                char tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        return String.valueOf(arr);
    }

    private boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
