package bit;

/**
 * 不用+/-求两数之和：
 *
 * 给你两个整数，在不使用+/-这两个运算符的前提下，求它们的和
 *
 * 其实就是使用位运算、异或、与运算方式对二进制计算结果
 *
 * 与运算&规则：对应位全是1结果为1，否则为0
 *
 * 或运算|规则：对应位全是0结果为0，否则为1
 *
 * 异或运算^规则：对应位相同结果为0，否则为1
 *
 * 十进制转二进制：将十进制的值不断与2相除取余直到商为0无法再相除，余数从下往上读就是二进制数
 *
 * 比如：
 * 十进制9
 * 9 / 2 = 4......1
 * 4 / 2 = 2......0
 * 2 / 2 = 1......0
 * 1 / 2 = 0......1
 *
 * 所以十进制9的二进制数从下往上读为1001
 *
 * 十进制11
 * 11 / 2 = 5.....1
 * 5 / 2 = 2......1
 * 2 / 2 = 1......0
 * 1 / 2 = 0......1
 *
 * 所以十进制11的二进制数从下往上读为1011
 *
 * 二进制转十进制：二进制的每一位进行阶乘
 *
 * 1001 = 1*2^3 + 0*2^2 + 0*2^1 + 1*2^0 = 8 + 0 + 0 + 1 = 9
 * 1011 = 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0 = 8 + 0 + 2 + 1 = 11
 */
public class GetSumNonSymbol {

    public static void main(String[] args) {
        GetSumNonSymbol main = new GetSumNonSymbol();
        System.out.println(main.getSumRecursive(9, 11));
        System.out.println(main.getSumInteractive(9, 11));
    }

    /**
     * 方式1：递归实现
     *
     * 当有一个数为0的时候说明加法完成，使用异或和与产生进位不断被0填充，肯定会有一个值是会全为0
     * 此时最终结果就是不为0的二进制值
     *
     * T:O(m)
     * S:O(1)
     */
    private int getSumRecursive(int a, int b) {
        return b == 0 ? a : getSumRecursive((a ^ b), (a & b) << 1);
    }

    /**
     * 方式2：迭代实现
     *
     * T:O(m)
     * S:O(1)
     */
    private int getSumInteractive(int a, int b) {
       while (b != 0) {
           int sum = a ^ b;
           int carry = (a & b) << 1;
           a = sum;
           b = carry;
       }
       return a;
    }
}
