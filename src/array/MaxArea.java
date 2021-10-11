package array;

/**
 * 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 * 示例1：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 *
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 *
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 *
 * 输入：height = [1,2,1]
 * 输出：2
 *
 * 提示：
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class MaxArea {

    /**
     * 双指针
     *
     * 假设示例为以下数组：
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *  l                       r
     *
     * 容纳水量的公式为：两个指针指向的数字中的较小值 * 指针之间的距离
     *
     * 要移动数值小的指针，最终才能计算出最大的容量。步骤如下：
     *
     * 1、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *  l                       r
     * height[l] < height[r], area = height[l] * (r - l) = 1 * (8 - 0) = 8
     *
     * 2、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l                    r
     * height[l] > height[r], area = height[r] * (r - l) = 7 * (8 - 1) = 49
     *
     * 3、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l                 r
     * height[l] > height[r], area = height[r] * (r - l) = 3 * (7 - 1) = 18
     *
     * 4、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l              r
     * height[l] == height[r], area = height[r] * (r - l) = 8 * (6 - 1) = 40
     *
     * 5、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l           r
     * height[l] > height[r], area = height[r] * (r - l) = 4 * (5 - 1) = 16
     *
     * 6、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l        r
     * height[l] > height[r], area = height[r] * (r - l) = 5 * (4 - 1) = 15
     *
     * 7、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l     r
     * height[l] > height[r], area = height[r] * (r - l) = 2 * (3 - 1) = 4
     *
     * 8、
     * [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *     l  r
     * height[l] > height[r], area = height[r] * (r - l) = 6 * (2 - 1) = 6
     *
     * 最终 maxArea = 49
     *
     * 具体题解：https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
     *
     * T:O(n)
     * S:O(1)
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int leftVal = height[left];
            int rightVal = height[right];
            maxArea = Math.max(maxArea, Math.min(leftVal, rightVal) * (right - left));
            if (leftVal > rightVal) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}
