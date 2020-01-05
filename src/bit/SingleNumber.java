package bit;

import java.util.HashSet;
import java.util.Set;

/**
 * 单身数字：
 *
 * 给你一个非空的整数数组，这个数组中有一个整数只出现了一次，其它的整数都出现两次，你要找出这个只出现一次的整数
 *
 * 比如说，给你的数组是：
 *
 * 5, 7, 5, 6, 6
 *
 * 这里 7 只出现了一次，因此你要返回的就是 7
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
