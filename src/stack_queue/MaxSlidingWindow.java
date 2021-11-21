package stack_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239.滑动窗口的最大值
 *
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 示例 3：
 *
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 *
 * 示例 4：
 *
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 *
 * 示例 5：
 *
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 */
public class MaxSlidingWindow {

    /**
     * 为了能实现每次滑动窗口移动都能拿到最大值，需要自定义一个单调递减队列
     * 1、add(val)：如果val大于队列中的数值，依次从后向前弹出，保证从队头到队尾是递减的，队头是最大值
     * 2、poll(val)：如果val与队头相同，则弹出
     * 3、peek()：获取队头最大值
     *
     * 1、
     * [1,3,-1,-3,5,3,6,7], k = 3
     * -------
     * queue = [3, -1], max = 3
     *
     * 2、
     * [1,3,-1,-3,5,3,6,7], k = 3
     *    ------
     * queue = [3, -1], max = 3
     *
     * 3、
     * [1,3,-1,-3,5,3,6,7], k = 3
     *      -------
     * queue = [5], max = 5
     *
     * 4、
     * [1,3,-1,-3,5,3,6,7], k = 3
     *         ------
     * queue = [5, 3], max = 5
     *
     * 5、
     * [1,3,-1,-3,5,3,6,7], k = 3
     *            -----
     * queue = [6], max = 6
     *
     * 6、
     * [1,3,-1,-3,5,3,6,7], k = 3
     *              -----
     * queue = [7], max = 7
     *
     * 具体题解：https://gitee.com/programmercarl/leetcode-master/blob/master/problems/0239.%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%9C%80%E5%A4%A7%E5%80%BC.md
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int len = nums.length - k + 1;
        int[] result = new int[len];
        int num = 0;

        MyQueue queue = new MyQueue();
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        result[num++] = queue.peek(); // 记录第一个滑动窗口的最大值

        for (int i = k; i < nums.length; i++) {
            queue.poll(nums[i - k]); // 滑动窗口往前移动，前面数值和队头相同则弹出
            queue.add(nums[i]); // 新数值如果符合条件则入队
            result[num++] = queue.peek(); // 记录每次窗口移动后的最大值
        }
        return result;
    }

    private static class MyQueue {
        private final Deque<Integer> queue = new LinkedList<>();

        public void add(int val) {
            while (!queue.isEmpty() && val > queue.getLast()) {
                queue.removeLast();
            }
            queue.add(val);
        }

        public void poll(int val) {
            if (!queue.isEmpty() && val == queue.peek()) {
                queue.poll();
            }
        }

        public int peek() {
            return queue.peek();
        }
    }
}
