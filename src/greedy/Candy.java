package greedy;

/**
 * 135.分发糖果
 *
 * 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例1：
 *
 * 输入：[1,0,2]
 * 输出：5
 * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例2：
 *
 * 输入：[1,2,2]
 * 输出：4
 * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class Candy {

    /**
     * 思路：
     * 这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边
     * 如果两边一起考虑一定会顾此失彼！
     *
     * 先确定右边评分大于左边的情况（也就是从前向后遍历）
     * 局部最优：只要右边评分比左边大，右边的孩子就多一个糖果
     * 全局最优：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果
     * 局部最优可以推出全局最优
     * 如果 ratings[i] > ratings[i-1], 那么 ratings[i] 的糖一定要比 ratings[i-1] 的糖多一个
     * 所以贪心：candies[i] = candies[i-1]+1
     *
     * // 从前向后
     * for (int i = 1; i < ratings.length; i++) {
     *     if (ratings[i] > ratings[i-1]) {
     *         candies[i] = candies[i-1] + 1;
     *     }
     * }
     *
     * 下标：              0  1  2  3  4  5  6
     * ratings：          1  2  2  5  4  3  2
     * 从左到右分发candy：  1  2  1  2  1  1  1
     *
     *
     * 再确定左孩子大于右孩子的情况（从后向前遍历）
     * 为什么不能从前向后遍历呢？因为如果从前向后遍历，根据 ratings[i+1] 来确定 ratings[i] 对应的糖果，那么每次都不能利用上前一次的比较结果了
     * 所以确定左孩子大于右孩子的情况一定要从后向前遍历！
     * 如果 ratings[i] > ratings[i+1]，此时 candies[i]（第 i 个小孩的糖果数量）就有两个选择：
     * 1、candies[i+1]+1：比右孩子的糖果数量+1
     * 2、candies[i]：使用之前比较右孩子大于左孩子得到的糖果数量
     *
     * 局部最优：取 candies[i+1]+1 和 candies[i] 最大的糖果数量，保证第 i 个小孩的糖果数量即大于左边也大于右边的
     * 全局最优：相邻的孩子中，评分高的孩子获得更多的糖果
     * 局部最优可以推出全局最优
     * 所以就取 candies[i+1]+1 和 candies[i] 最大的糖果数量，candies[i] 只有取最大的才能既保持对左边 candies[i-1] 的糖果多，也比右边 candies[i+1] 的糖果多
     *
     * 下标：              0  1  2  3  4  5  6
     * ratings：          1  2  2  5  4  3  2
     * 从左到右分发candy：  1  2  1  2  1  1  1
     * 从右向左比较右孩子：                    1
     *   i 为5的时候                    <-----  i为5，此时 ratings[i] > ratings[i+1]，candies[i] = max(candies[i], candies[i+1]+1)
     * 从右到左分发candy：  1  2  1  4  3  2  1
     *
     * // 从后向前
     * for (int i = ratings.length - 2; i >= 0; i--) {
     *     if (ratings[i] > ratings[i + 1]) {
     *         candies[i] = Math.max(candies[i], candies[i + 1] + 1);
     *     }
     * }
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        // 从前向后遍历，先确定右边评分高的孩子比左边孩子糖果数量+1
        for (int i = 1; i < ratings.length; i++) {
            // 右孩子评分 > 左孩子评分
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // 从后向前遍历
        for (int i = ratings.length - 2; i >= 0; i--) {
            // 左孩子评分 > 右孩子评分
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int size = 0;
        for (int candy : candies) {
            size += candy;
        }
        return size;
    }
}
