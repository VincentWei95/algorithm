package backtracking.partition;

import java.util.ArrayList;
import java.util.List;

/**
 * 131.分割回文串
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Partition {

    /**
     * 本题主要设计两个关键问题：
     * 1、切割问题，有不同的切割方式
     * 2、判断回文
     *
     * 回溯究竟是如何切割字符串的呢？
     * 我们分析一下切割，其实切割问题类似组合问题
     * 例如对于字符串abcdef：
     * 组合问题：选取一个a之后，在bcdef中再去选取第二个，选取b之后在cdef中再选取第三个...
     * 切割问题：切割一个a之后，在bcdef中再去切割第二段，切割b之后在cdef中再切割第三段...
     *
     * 所以切割问题，也可以抽象为一颗树形结构：
     *
     *                                  在a a b中截取
     *                           /        \             \
     *                       截取a        截取aa        截取aab
     *                       /              \             \
     *          a|a b，在a b 中截取       a a|b，在b中截取  a a b|，剩下为空
     *                    /     \            \
     *                  截取a   截取ab        截取b
     *                 /          \            \
     *         a|a|b，在b中截取    ab不是回文   a a|b|，切割完毕
     *              /
     *            截取b
     *            /
     *         a|a|b|，切割完毕
     *
     * 图中 | 就是切割线，切割线切割到字符串的结尾位置，说明找到了一个切割方法
     *
     * 1、确定递归函数参数和返回值
     * 参数 List<List<Integer>> result 存放符合条件的回文字符串结果集
     * 参数 List<Integer> path 记录符合回文条件的字符串
     * 参数 int startIndex 用于保证切割过的地方不能重复切割
     * 返回值 void
     *
     * 2、确定递归终止条件
     * 当 startIndex >= s.length() 时，切割线切到了字符串最后面，说明找到了一种切割方案
     *
     * 3、确定单层递归逻辑
     * for (int i = startIndex; i < s.length(); i++) 循环中，定义了起始位置 startIndex
     * 那么 [startIndex, i] 就是要截取的子串
     * 首先判断子串是不是回文，如果是回文则加入到 path，path 记录切割过的回文子串
     *
     * for (int i = startIndex; i < s.length(); i++) {
     *     if (isPalindrome(s, startIndex, i)) { // [startIndex, i] 是回文子串
     *         String str = s.substring(startIndex, i + 1);
     *         path.add(str); // 记录 [startIndex, i] 的回文子串
     *     } else {
     *         continue; // 不是回文子串直接跳过
     *     }
     *     backtracking(s, i + 1); // 传递 i + 1 不重复切割
     *     path.remove(path.size() - 1); // 回溯
     * }
     */
    private final List<List<String>> result = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    private void backtracking(String s, int startIndex) {
        // 起始位置大于字符串大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 如果是回文字符串，则记录
            if (isPalindrome(s, startIndex, i))  {
                String palindromeStr = s.substring(startIndex, i + 1);
                path.add(palindromeStr);
            } else {
                continue;
            }
            // i + 1 起始位置后移，保证不重复
            backtracking(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
