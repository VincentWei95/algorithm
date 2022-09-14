package backtracking.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 17.电话号码的字母组合
 *
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *   1          2(abc)          3(def)
 *   4(ghi)     5(jkl)          6(mno)
 *   7(pqrs)    8(tuv)          9(wxyz)
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LetterCombinations {

    /**
     * 思路：
     * digits = "23"
     *
     *                     abc
     *              /       |       \
     *            def     def       def
     *          / \ \    / \ \     / \ \
     *         ad ae af bd be bf  cd ce cf
     *
     * 首先需要思考的问题是？怎么把数字映射为字母？直接思考 digits = "23" 可能想不明白
     * 换个角度，如果 digits = "abcdef"，获取字母组合，很容易就能联想到：
     * for (int i = 0; i < "abc".length(); i++) {
     *     for (int j = 0; j < "def".length(); j++) {
     *
     *     }
     * }
     * 回溯时最终是要使用的字符数组遍历，数字映射字符串使用 map
     *
     * 1、确定递归函数参数和返回值
     * 参数 List<String> result 存放结果集
     * 参数 StringBuilder 记录每个节点
     * 参数 size 记录在当前层的节点数量，用于递归终止条件的判断
     * 参数 digits 字符串
     * 参数 map 存储的每个数字的映射字母集合
     * 回溯函数一般不需要返回值
     *
     * 2、确定递归函数终止条件
     * 当 size == digits.length()，result 保存 StringBuilder 记录的字母
     *
     * 3、确定单层递归逻辑
     * for 循环，因为每个数字代表的一个字母集合，所以循环范围是[0, map[n].length]
     * StringBuilder 记录每个字母
     * 然后进入递归
     * 递归完成后 StringBuilder 要撤销记录的字母
     */
    private final List<String> result = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(digits, map, 0);
        return result;
    }

    private void backtracking(String digits, String[] map, int size) {
        if (size == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String str = map[digits.charAt(size) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backtracking(digits, map, size + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
