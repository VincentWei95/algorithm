package math;

import java.util.HashSet;
import java.util.Set;

/**
 * 136.只出现一次的数字：
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumber {

    public static void main(String[] args) {
        SingleNumber main = new SingleNumber();
        System.out.println(main.SingleNumWithXOR(new int[]{5, 7, 5, 6, 6}));
    }

    /**
     * 方式1：Set实现
     *
     * Set不存储重复的数值，所以最终会存入的是不重复的数字
     * 用一个临时变量记录下来不重复的数值的和，以及数组所有数值的和
     * 最终2倍的不重复数值的和 - 数组所有数值的和就是最终结果
     *
     * T:O(n) 循环遍历一次
     * S:O(n) 需要一个Set集合存储
     */
    private int SingleNumberWithSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int uniqueSum = 0;
        int totalSum = 0;
        for (int num : arr) {
            if (!set.contains(num)) {
                uniqueSum += num;
                set.add(num);
            }
            totalSum += num;
        }
        return 2 * uniqueSum - totalSum;
    }

    /**
     * 方式2：异或运算实现
     *
     * 异或运算有以下三个性质：
     * 1、任何数和0做异或运算，结果仍然是原来的数，即 a ^ 0 = a
     * 2、任何数和其自身做异或运算，结果是0，即 a ^ a = 0
     * 3、异或运算满足交换律和结合律，即 a ^ b ^ a = b ^ a ^ a = b ^ (a ^ a) = b ^ 0 = b
     *
     * 假设数组 [2, 2, 1]
     *
     * 第一次遍历：result=0, result ^ num = 0 ^ 2 = 2
     * 第二次遍历：result=2, result ^ num = 2 ^ 2 = 0
     * 第三次遍历：result=0, result ^ num = 0 ^ 1 = 1
     *
     * T:O(n)
     * S:O(1)
     */
    private int SingleNumWithXOR(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
