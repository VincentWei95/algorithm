package string;

/**
 * 实现StrStr：
 *
 * 你要实现 C 语言里面的 strstr 函数，这个函数接收两个字符串，你要找到第二个字符串在第一个字符串中的开始下标，如果找不到则返回 -1
 *
 * 比如说，给你的两个字符串分别是：
 *
 * marvel studio 和 studio
 *
 * 第二个字符串存在于第一个字符串中，于是你要返回它在第一个字符串中的开始下标 7
 *
 * 再比如说给你的字符串是：
 *
 * doctor strange 和 master
 *
 * 第二个字符串没有在第一个字符串中出现，因此返回 -1
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr main = new StrStr();
        System.out.println(main.strstr("marvel studio", "studio"));
    }

    /**
     * 标准实现
     *
     * 遍历字符串找到和需要的字符串首字符相同的字符，然后开始再循环往后的字符直到对比完所需字符串是否完全相同
     * 如果是则返回外层循环的索引位置，否则返回-1
     *
     * T:O((n-m+1)*m)
     * S:O(1)
     */
    private int strstr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.length() == 0) return 0;
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0; // 内层循环遍历needle的索引位置
            int k = i; // 外层循环找到haystack和needle开始字符相同的索引位置
            // 外层找到和needle开始字符相同的字符，开启循环遍历haystack往后的字符是否和needle相同
            for (; j < m && k < n && needle.charAt(j) == haystack.charAt(k); j++, k++);
            // 能遍历完needle字符串说明找到，直接返回起始索引位置
            if (j == needle.length()) return i;
        }
        return -1;
    }

    /**
     * 个人思路实现
     */
    private int strstr2(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.length() == 0) return 0;

        char needleBeginChar = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            char haystackChar = haystack.charAt(i);
            boolean find = false;
            if (haystackChar == needleBeginChar) {
                for (int j = 0; j < needle.length(); j++) {
                    find = haystack.charAt(i + j) == needle.charAt(j);
                }
            }
            if (find) return i;
        }
        return -1;
    }
}
