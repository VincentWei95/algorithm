package doublepointer.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 */
public class ThreeSum {

    /**
     * 1、先对数组排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i] 后面的两端，
     * 数字分别为 nums[l] 和 nums[r]，计算三个数的和sum判断是否满足为0，满足则添加进结果集合
     *
     * 2、如果 nums[i] > 0，则三数之和必然无法等于0，结束循环
     *
     * 3、如果 nums[i] == nums[i - 1]，则说明该数字重复，会导致结果重复，所以跳过
     *
     * 4、
     * 当 sum == 0 时，nums[l] == nums[l + 1] 则会导致结果重复，应该跳过 l++
     * 当 sum == 0 时，nums[r] == nums[r - 1] 则会导致结果重复，应该跳过 r--
     *
     * 1、
     * [-1, 0, 1, 2, -1, -4] -> [-4, -1, -1, 0, 1, 2]
     *
     *
     * 2、
     * (1)
     * [-4, -1, -1, 0, 1, 2]
     *  i   l             r
     * nums[i] + nums[l] + nums[r] = (-4) + (-1) + 2 = -3, sum < 0, l++
     * [-4, -1, -1, 0, 1, 2]
     *  i        l        r
     *
     * (2)
     * [-4, -1, -1, 0, 1, 2]
     *  i           l     r
     * nums[i] + nums[l] + nums[r] = (-4) + 0 + 2 = -2, sum < 0, l++
     * [-4, -1, -1, 0, 1, 2]
     *  i              l  r
     *
     * (3)
     * [-4, -1, -1, 0, 1, 2]
     *  i              l  r
     * nums[i] + nums[l] + nums[r] = (-4) + 1 + 2 = -1, sum < 0, l++
     *
     * 3、
     * (1)
     * [-4, -1, -1, 0, 1, 2]
     *      i   l         r
     * nums[i] + nums[l] + nums[r] = (-1) + (-1) + 2 = 0, list=[[-1, -1, 2]]
     *
     * (2)
     * [-4, -1, -1, 0, 1, 2]
     *      i       l     r
     * nums[i] + nums[l] + nums[r] = (-1) + 0 + 2 = 1, sum > 0, r--
     *
     * (3)
     * [-4, -1, -1, 0, 1, 2]
     *      i       l  r
     * nums[i] + nums[l] + nums[r] = (-1) + 0 + 1 = 0, list=[[-1, -1, 2], [-1, 0, 1]]
     *
     * 4、
     * [-4, -1, -1, 0, 1, 2]
     *              i  l  r
     * nums[i] + nums[l] + nums[r] = 0 + 1 + 2 = 3, sum > 0, r--, l == r， break
     *
     * 5、
     * [-4, -1, -1, 0, 1, 2]
     *                 i
     * i > 0, break
     *
     * T:O(n2)
     * S:O(n)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) return list;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 因为l和r是在i后面的，列表已经排序，当i索引的值>0，后面的值都会>0可以直接跳出循环
            if (nums[i] > 0) break;
            // 和上一个数值对比，如果相同则去重跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 和后面的相同去重跳过
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    // 和前面的相同去重跳过
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    // 因为排序过，和小于0说明left比较大，要往后走
                    l++;
                } else {
                    // 因为排过序，和大于0说明right比较大，要往前走
                    r--;
                }
            }
        }
        return list;
    }
}
