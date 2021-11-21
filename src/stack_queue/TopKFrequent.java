package stack_queue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 347.前k个高频元素
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 *
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 */
public class TopKFrequent {

    /**
     * T:O(nlogk)：n为数组长度
     * 首先遍历数组，并用哈希表记录出现次数，每个元素需要O(1)时间，共需O(n)
     * 随后，小顶堆大小至多为k，因此每次堆操作需要O(logk)时间，共需O(nlogn)
     *
     * S:O(n)：哈希表大小为O(n)，堆大小为O(k)，共为O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        // map记录数字出现的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 因为最终要输出频率最高的前k个数值，所以需要用一个小顶堆（数值小的在队头，数值大的在队尾）按出现频率排序
        // 按频率从小到大排序，如果队列的长度 > k 时，要将队头出现频率小的数值弹出，最终保留出现频率高的数值
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry); // 将数值添加到队列
            if (queue.size() > k) { // 弹出队头频率小的数值
                queue.poll();
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey(); // 队尾是出现频率最大的，所以从后往前遍历
        }
        return result;
    }
}
