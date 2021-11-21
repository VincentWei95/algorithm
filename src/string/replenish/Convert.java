package string.replenish;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.Z字形变换
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class Convert {

    /**
     * Z字形变换，实际上是要将排列后的字符串用N字母看，如 PAYPALISHIRING
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * 题目理解：
     * 1、字符串 s 是以 Z 字形为顺序存储的字符串，目标是按行打印
     * 2、设 numRows 行字符串分别为S1,S2,...,Sn，则容易发现：按顺序遍历字符串 s 时，
     * 每个字符 c 在 Z 字形中对应的行索引从 S1 增大到 Sn，再从 Sn 减小的 S1，如此反复
     * 3、因此，解决方案为：模拟这个行索引的变化，在遍历 s 中把每个字符填到正确的的行res[i]
     *
     * 算法流程：按顺序遍历字符串 s
     * 1、res[i] += c：把每个字符 c 填入对应航 Si
     * 2、i += flag：更新当前字符 c 对应的行索引
     * 3、flag = -flag：在达到 Z 字形转折点时，执行反向
     *
     * 步骤：
     * LEETCOD
     *
     * 1、
     * L   C    res[0] = L
     * E T O    res[1] =
     * E   D    res[2] =
     *
     * i = 0, numRows = 3, flag = -flag = -(-1) = 1
     * i = i + flag = 0 + 1 = 1
     *
     * 2、
     * L   C    res[0] = L
     * E T O    res[1] = E
     * E   D    res[2] =
     *
     * i = 1, numRows = 3, flag = 1
     * i = i + flag = 1 + 1 = 2
     *
     * 3、
     * L   C    res[0] = L
     * E T O    res[1] = E
     * E   D    res[2] = E
     *
     * i = 2, numRows = 3, flag = -flag = -1
     * i = i + flag = 2 + (-1) = 1
     *
     * 4、
     * L   C    res[0] = L
     * E T O    res[1] = ET
     * E   D    res[2] = E
     *
     * i = 1, numRows = 3, flag = -1
     * i = i + flag = 1 + (-1) = 0
     *
     * 5、
     * L   C    res[0] = LC
     * E T O    res[1] = ET
     * E   D    res[2] = E
     *
     * i = 0, numRows = 3, flag = -flag = 1
     * i = i + flag = 0 + 1 = 1
     *
     * 6、
     * L   C    res[0] = LC
     * E T O    res[1] = ETO
     * E   D    res[2] = E
     *
     * i = 1, numRows = 3, flag = 1
     * i = i + flag = 1 + 1 = 2
     *
     * 5、
     * L   C    res[0] = LC
     * E T O    res[1] = ETO
     * E   D    res[2] = ED
     *
     * i = 2, numRows = 3, flag = -flag = -1
     * i = i + flag = 2 + (-1) = 1
     *
     * 6、
     * res[0] = LC
     * res[1] = ETO
     * res[2] = ED
     *
     * res = res[0] + res[1] + res[2] = LCETOED
     *
     * T:O(n)
     * S:O(n)
     */
    public String convert(String s, int numRows) {
        // 如果只有一行，直接返回字符串
        if (numRows < 2) return s;

        // 提供每一行放置字符的容器
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0; // 遍历行的索引
        int flag = -1; // 用于换行
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            rows.get(i).append(c);
            // 如果到达第一行或最后一行，flag控制换行处理
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }

        // 将每一行存储的字符合并
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
