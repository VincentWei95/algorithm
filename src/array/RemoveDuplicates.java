package array;

/**
 * 删除数组中的重复元素：
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
 *
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

    }

    /**
     * 双指针实现
     *
     * [1, 1, 2]
     * p  q
     * p = 0, q = 1, nums[p] = 1, nums[q] = 1, nums[p] == nums[q], q = 2
     *
     * [1, 1, 2]
     * p      q
     * p = 0, q = 2, nums[p] = 1, nums[q] = 2, nums[p] != nums[q], 交换, p = 1, q = 2
     *
     * [1, 2, 2]
     *     p  q   p指向的是不重复的元素索引
     *
     * [1, 2]
     */
    private int removeDuplicates(int[] nums) {
        int p = 0;
        for (int q = 1; q < nums.length; q++) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
        }
        return p + 1; // p是索引，返回长度需要+1
    }
}
