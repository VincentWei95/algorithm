package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的众数
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 */
public class FindMode {

    public static void main(String[] args) {

    }

    /**
     * 二叉搜索树的中序遍历可以获取到一个升序的元素列表，如果出现众数，可以发现众数的出现会是连续的
     *
     * 需要提供四个变量：
     * 1、base：记录当前的数字
     * 2、count：记录当前数字重复的次数
     * 3、maxCount：维护出现过的众数的最大次数
     * 4、list：记录众数列表
     *
     * 递归思路：
     * 1、首先更新base和count：
     * （1）如果该元素和base相等，那么count自增1
     * （2）否则将base更新为当前数字，count复位为1
     * 2、然后更新maxCount：
     * （1）如果count==maxCount，说明当前这个数字（base）出现的次数等于当前众数出现的次数，将base加入到list列表
     * （2）如果count>maxCount，说明当前这个数字（base）出现的次数大于当前众数出现的次数，将maxCount更新为count，清空list列表然后添加base为新的众数
     *
     * T:O(n)
     * S:O(n)
     */
    private int base;
    private int count;
    private int maxCount;
    private List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        if (root.val == base) {
            count++;
        } else {
            base = root.val;
            count = 1;
        }
        if (count == maxCount) {
            list.add(base);
        } else if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(base);
        }
        dfs(root.right);
    }
}
