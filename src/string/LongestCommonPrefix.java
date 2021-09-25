package string;

/**
 * 最长公共前缀：
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀，如果不存在公共前缀，返回空字符串 ""
 *
 * 输入：["flower","flow","flight"]
 * 输出："fl"
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };

        LongestCommonPrefix main = new LongestCommonPrefix();
        System.out.println(main.longestCommonPrefix(strs));
    }

    /**
     * 执行步骤：
     * 1、prefix=flower，str[1]=flow
     * 一次循环：str[1].indexOf(prefix) > 0，prefix=flowe
     * 二次循环：str[1].indexOf(prefix) > 0，prefix=flow
     *
     * 2、prefix=flo，str[2]=flight
     * 一次循环：str[2].indexOf(prefix) > 0，prefix=flo
     * 二次循环：str[2].indexOf(prefix) > 0，prefix=fl
     *
     * T:O(mn)，m是字符串数组中字符串的平均长度，n是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次
     * S:O(1)
     */
    private String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            // 找到有公共的前缀
            while (strs[i].indexOf(prefix) != 0) {
                // 每次循环将当前prefix的字符往后减少一位后再和当前字符串数组元素对比有没有相同的公共前缀，直到完全相同
                prefix = prefix.substring(0, prefix.length() - 1);
                System.out.println("i =" + i + ", prefix = " + prefix);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }
}
