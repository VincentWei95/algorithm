package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中超过一半的数字：
 *
 * 给你一个数组，里面有一个数字出现的次数超过了一半，你要找到这个数字并返回
 *
 * 比如说，给你的数组是
 *
 * 1, 3, 3, 1, 3, 1, 1
 *
 * 这个数组的长度是 7，这里我们只考虑整数除法，长度 7 除以 2 是 3。数组里面 1 出现了 4 次，超过了一半的数量 3，因此你要返回的就是 1
 */
public class GetMajority {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 3, 1, 3, 1, 1 };

        GetMajority main = new GetMajority();
        System.out.println(main.getMajorityVout(arr));
    }

    /**
     * 方式1：HashMap存储判断
     *
     * T:O(n)
     * S:O(n)
     */
    private int getMajorityHashMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxNum = 0; // 记录元素
        int maxCount = 0; // 记录出现次数
        for (int element : arr) {
            // key:element value:count
            int newCount = map.getOrDefault(element, 0) + 1;
            map.put(element, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
                maxNum = element;
            }
        }
        return maxNum;
    }

    /**
     * 方式2：摩尔投票算法
     *
     * 提供一个计数器，遇到相同的数字计数器+1，遇到不同的数字计数器-1
     *
     * T:O(n)
     * S:O(1)
     */
    private int getMajorityVout(int[] arr) {
        int num = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            // 当前计数器为0，更新当前元素和计数器+1
            if (count == 0) {
                num = arr[i];
                count = 1;
            }
            // 元素相同计数器+1
            else if (arr[i] == num) {
                count++;
            }
            // 元素不同计数器-1
            else {
                count--;
            }
        }

        return num;
    }
}
