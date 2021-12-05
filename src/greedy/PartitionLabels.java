package greedy;

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
     * 思路：
     * 在遍历的过程中相当于是要找每一个字母的边界，如果找到之前遍历过的所有字母的最远边界，说明这个边界就是分割点了。
     * 此时前面出现过的所有字母，最远也就到这个边界。
     *
     * 可以分为如下两步：
     * 1、统计每一个字符最后出现的位置
     * 2、从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等，则找到了分割点
     *
     * 下标：              0  1  2  3  4  5  6  7  8 ｜ 9  10  11  12  13  14  15 ｜ 16  17  18  19  20  21  22  23|
     * 字符串：            a  b  a  b  c  b  a  c  a ｜ d   e   f   e   g   d   e ｜  h   i   j   h   k   l   i   j|
     * 字符最后出现位置：   8  5  8  5  7  5  8  7   8 ｜14  15  11  15  13  14  15 ｜ 19  22  23  19  20  21  22  23|
     *                   ｜                       ｜              ｜           ｜            ｜                  |
     *                更新最远边界为8             right            更新最远     right         更新最远             right
     *                                      找到之前字符最大出      边界为15  找到之前字符最大出 边界为23           找到之前字符最大出
     *                                       现位置和下标相等                 现位置和下标相等                    现位置和下标相等
     * T:O(n)
     * S:O(1)
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i; // 记录每一个字符最后出现的位置
        }

        int index = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            index = Math.max(index, edge[chars[i] - 'a']); // 找到字符出现的最远边界
            if (i == index) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }
}
