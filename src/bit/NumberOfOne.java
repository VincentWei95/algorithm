package bit;

/**
 * 二进制中1的数：
 *
 * 给你一个整数，你要计算它的二进制表示中 1 的个数，然后返回
 *
 * 比如说，给你的整数是 12，它的二进制表示是：
 *
 * 1100
 *
 * 包含两个 1，因此你要返回 2
 */
public class NumberOfOne {

    public static void main(String[] args) {
        NumberOfOne main = new NumberOfOne();
        System.out.println(main.numberOfOneMask(12));
    }

    /**
     * T:O(k)
     * S:O(1)
     */
    private int numberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    /**
     * T:O(m)
     * S:O(1)
     */
    private int numberOfOneMask(int n) {
        int mask = 1;
        int count = 0;
        while (mask != 0) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}
