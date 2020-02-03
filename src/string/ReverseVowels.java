package string;

/**
 * 反转字符串元音字母：
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母
 *
 * 输入: "hello"
 * 输出: "holle"
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
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
