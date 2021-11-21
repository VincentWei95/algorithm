package doublepointer.window;

import java.util.HashMap;

/**
 * 904.水果成篮
 *
 * 在一排树中，第 i 棵树产生tree[i] 型的水果。
 * 你可以从你选择的任何树开始，然后重复执行以下步骤：
 *
 * 1、把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
 * 2、移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
 *
 * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
 *
 * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
 *
 * 用这个程序你能收集的水果树的最大总量是多少？
 *
 * 示例 1：
 *
 * 输入：[1,2,1]
 * 输出：3
 * 解释：我们可以收集 [1,2,1]。
 * 示例 2：
 *
 * 输入：[0,1,2,2]
 * 输出：3
 * 解释：我们可以收集 [1,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
 * 示例 3：
 *
 * 输入：[1,2,3,2,2]
 * 输出：4
 * 解释：我们可以收集 [2,3,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
 * 示例 4：
 *
 * 输入：[3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：我们可以收集 [1,2,1,1,2]
 * 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。
 */
public class TotalFruit {

    public static void main(String[] args) {
        TotalFruit main = new TotalFruit();
        main.totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4});
    }

    /**
     * 滑动窗口
     *
     * 这道题目可以理解为：寻找最大不同类型的子序列，查找到满足条件的区间[i, j]
     *
     * 1、
     * [3,3,3,1,2,1,1,2,3,3,4]
     *  j
     *  i
     * i = 0, j = 0, ans = 0
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *        j
     *  i
     * map(3, 3)
     * map(1, 1)
     * i = 0, j = 3, ans = 4
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *          j
     *        i
     * map(1, 1)
     * map(2, 1)
     * i = 3, j = 4, ans = max(ans, j - i + 1) = max(4, 2) = 4
     *
     * 2、
     * [3,3,3,1,2,1,1,2,3,3,4]
     *          j
     *        i
     * map(1, 1)
     * map(2, 1)
     * i = 3, j = 4, ans = 4
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *                j
     *        i
     * map(1, 3)
     * map(2, 2)
     * i = 3, j = 7, ans = 5
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *                  j
     *                i
     * map(2, 1)
     * map(3, 1)
     * i = 7, j = 8, ans = max(ans, j - i + 1) = max(5, 2) = 5
     *
     * 3、
     * [3,3,3,1,2,1,1,2,3,3,4]
     *                  j
     *                i
     * map(2, 1)
     * map(3, 1)
     * i = 7, j = 8, ans = 5
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *                    j
     *                i
     * map(2, 1)
     * map(3, 2)
     * i = 7, j = 9, ans = 5
     *
     * [3,3,3,1,2,1,1,2,3,3,4]
     *                      j
     *                  i
     * map(3, 2)
     * map(4, 1)
     * i = 8, j = 10, ans = max(ans, j - i + 1) = max(5, 3) = 5
     *
     * T:O(n)
     * S:O(n)
     */
    public int totalFruit(int[] fruits) {
        int i = 0;
        int ans = 0;
        Counter counter = new Counter();
        for (int j = 0; j < fruits.length; j++) {
            counter.add(fruits[j], 1);
            while (counter.size() >= 3) { // 因为只需要两种类型的水果，当存储的类型超过3种，就要移除一种类型
                counter.add(fruits[i], -1);
                if (counter.get(fruits[i]) == 0) {
                    counter.remove(fruits[i]);
                }
                i++;
            }
            System.out.println("j = " + j + ", i = " + i + ", ans = " + ans + ", j - i + 1 = " + (j - i + 1));
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    // key：水果类型
    // val：出现的次数
    private static class Counter extends HashMap<Integer, Integer> {
        public int get(int key) {
            return containsKey(key) ? super.get(key) : 0;
        }

        public void add(int key, int val) {
            put(key, get(key) + val);
        }
    }
}
