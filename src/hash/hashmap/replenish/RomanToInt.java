package hash.hashmap.replenish;

import java.util.HashMap;
import java.util.Map;

/**
 * 13.罗马数字转整数
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内
 *
 * 输入: "III"
 * 输出: 3
 *
 * 输入: "IV"
 * 输出: 4
 *
 * 输入: "IX"
 * 输出: 9
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class RomanToInt {

    public static void main(String[] args) {
        RomanToInt main = new RomanToInt();
        System.out.println(main.romanToInt("MCMXCIV"));
    }

    // 个人实现
    private int romanToInt(String str) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        for (int i = 0; i < str.length();) {
            char s = str.charAt(i);
            char t;
            // 判断下一个是否符合三个特殊条件
            if ((i + 1) <= str.length() - 1) {
                t = str.charAt(i + 1);
                if ((s == 'I' && t == 'V') || (s == 'I' && t == 'X')
                        || (s == 'X' && t == 'L') || (s == 'X' && t == 'C')
                        || (s == 'C' && t == 'D') || (s == 'C' && t == 'M')) {
                    sum = sum + (map.get(t) - map.get(s));
                    i += 2;
                    continue;
                }
            }
            sum += map.get(s);
            i++;
        }
        return sum;
    }

    /**
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     *
     * 例如 XXVII 可视作 X + X + V + I + I = 10 + 10 + 5 + 1 + 1 = 27
     *
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     *
     * 例如 XIV 可视作 X − I + V = 10 − 1 + 5 = 14
     */
    private int romainToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int value = map.get(s.charAt(i));
            if (i < length - 1 && value < map.get(s.charAt(i + 1))) {
                sum -= value;
            } else {
                sum += value;
            }
        }
        return sum;
    }
}
