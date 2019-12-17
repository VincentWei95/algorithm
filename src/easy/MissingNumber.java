package easy;

/**
 * 缺失的数字：
 *
 * 从 0 到 n 这 n+1 个整数中去掉一个，然后把剩下的 n 个整数放进一个长度为 n 的数组，给你这个数组，你要找到那个去掉的整数。
 *
 * 比如说，给你的数组是：
 *
 * 4, 1, 0, 2
 *
 * 这里的数组长度是 4，说明这是从 0 到 4 中去掉一个数字后形成的数组。数组中缺失的数字是 3，因此我们要返回 3。
 */
public class MissingNumber {

    public static void main(String[] args) {
        MissingNumber main = new MissingNumber();
        int[] arr = { 4, 1, 0, 2 };
        System.out.println(main.missingNumber(arr));
    }

    /**
     * 位运算异或实现
     *
     * 位运算异或：两个相同的值异或结果会是0
     *
     * 只需要把0到n的所有整数异或，再和数组里的所有数字异或，这样得到确实的数字，因为遍历了数组，每个值都出现了两次变为0，缺失的不为0
     *
     * T:O(n)
     * S:O(1)
     */
    private int missingNumber(int[] nums) {
        int n = nums.length;
        int result = n;
        for (int i = 0; i < n; i++) {
            result = result ^ i ^ nums[i];
        }
        return result;
    }
}
