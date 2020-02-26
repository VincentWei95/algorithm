package array;

/**
 * 移除元素：
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
 *
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElements {

    public static void main(String[] args) {

    }

    /**
     * 双指针实现：
     *
     * [3, 2, 2, 3]
     *  i  j         nums[j]=2 != val=3
     * [2, 2, 2, 3]
     *     i  j      nums[j]=2 != val=3
     * [2, 2, 2, 3]
     *        i  j  nums[j]=3 == val=3
     *
     * T:O(n) 数组元素为n时，i和j至少遍历2n步
     * S:O(1)
     */
    private int removeElements1(int[] nums, int val) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 双指针：优化
     *
     * T:O(n) i和n最多遍历n步
     * S:O(1)
     */
    private int removeElements2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1]; // 这步骤实际上使数组大小减少了1
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
