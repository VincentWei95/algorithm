package string.replenish;

import java.util.ArrayList;
import java.util.List;

/**
 * 763.划分字母区间
 *
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 */
public class PartitionLabels {

    /**
     * T:O(n)
     * S:O(1)
     *
     * 题解：
     * https://leetcode-cn.com/problems/partition-labels/solution/dai-ma-sui-xiang-lu-dai-ni-xue-tou-tan-x-czmo/
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] edge = new int[123];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i]] = i;
        }

        int index = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            index = Math.max(index, edge[chars[i]]);
            if (i == index) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }
}
