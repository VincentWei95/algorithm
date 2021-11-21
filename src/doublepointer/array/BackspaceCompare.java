package doublepointer.array;

/**
 * 844.比较含退格的字符串
 *
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，请你判断二者是否相等。# 代表退格字符。
 *
 * 如果相等，返回 true ；否则，返回 false 。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 *
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 “”。
 * 示例 3：
 *
 * 输入：s = "a##c", t = "#a#c"
 * 输出：true
 * 解释：s 和 t 都会变成 “c”。
 * 示例 4：
 *
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 “c”，但 t 仍然是 “b”。
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 */
public class BackspaceCompare {

    /**
     * 栈
     *
     * T:O(m+n)，m和n分别是字符串s和t的长度，因为要各自遍历一遍字符串
     * S:O(m+n)，分别需要容器添加字符后对比
     */
    public boolean backspaceCompareStack(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String str) {
        StringBuilder sb = new StringBuilder(); // 模拟栈
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '#') {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 从后向前双指针
     *
     * 两个字符串从后往前遍历，定义sSkip和tSkip分别记录跳过不对比的字符数量
     * 1、若字符为#，则我们需要多删除一个普通字符，即后续字符跳过不对比，sSkip或tSkip +1
     * 2、若字符为普通字符：
     * （1）若sSkip或tSkip不为0，则说明当前字符不需要对比跳过，并且sSkip或tSkip -1
     * （2）若sSkip或tSkip为0，对比字符是否相同
     *
     * s=ab#c，t=ad#c
     *
     * 1、
     * ab#c
     *    i
     * i = 3, s[i] != #, sSkip = 0
     *
     * ad#c
     *    j
     * j = 3, t[j] != #, tSkip = 0
     *
     * s[i] == s[j]
     *
     * 2、
     * ab#c
     *   i
     * i = 2, s[i] == #, sSkip = 1
     *
     * ad#c
     *   j
     * j = 2, t[j] == #, tSkip = 1
     *
     * 3、
     * ab#c
     *  i
     * i = 1, s[i] != #, sSkip = 1, sSkip - 1 = 0
     *
     * ad#c
     *  j
     * j = 1, t[j] != #, tSkip = 1, tSkip - 1 = 0
     *
     * 4、
     * ab#c
     * i
     * i = 0, s[i] != #, sSkip = 0
     *
     * ad#c
     * j
     * j = 0, t[j] != #, tSkip = 0
     *
     * s[i] == t[j]
     *
     * T:(m+n)，m和n分别是字符串s和t的长度，因为要各自遍历一遍字符串
     * S:O(1)
     */
    public boolean backspaceComparePointer(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int sSkip = 0;
        int tSkip = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    sSkip++; // 遇到#字符，跳过不对比的字符数量+1
                    i--;
                } else if (sSkip > 0) { // 有需要跳过不对比的字符数量，跳过字符
                    sSkip--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    tSkip++;
                    j--;
                } else if (tSkip > 0) {
                    tSkip--;
                    j--;
                } else {
                    break;
                }
            }

            // s或t字符串遍历完
            if (i < 0 || j < 0) break;
            // 字符不相同，直接返回结果
            if (s.charAt(i) != t.charAt(j)) return false;
            i--;
            j--;
        }
        // 字符串s和t都遍历完，说明字符串s和t相等
        return i == -1 && j == -1;
    }
}
