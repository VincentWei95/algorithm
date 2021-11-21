package tree.dfs;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 501.二叉搜索树中的众数
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
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

    /**
     * 根据二叉搜索树有序的特性，出现众数时数值会是连续的
     *
     * 定义变量 int count 记录当前数值出现的次数
     * 定义变量 int maxCount 记录数值出现的最大次数
     * 定义变量 TreeNode prev 记录上一个节点用于和当前节点数值对比
     * 定义变量 List<Integer> list 记录出现次数最大的数值
     *
     * 当 prev == null 时，count = 1
     * 当 prev.val == root.val 时，count++
     * 当 prev.val != root.val 时， count = 1
     *
     * 当 count == maxCount 时，将数值添加到列表中 list.add(root.val)
     * 当 count > maxCount 时，出现了最新的众数，此时更新 maxCount，并清空之前记录的众数，重新添加最新的众数
     * maxCount = count
     * list.clear()
     * list.add(root.val)
     *
     * 1、确定递归函数参数和返回值
     * 参数需要根节点
     * 因为是遍历整棵树，所以不需要返回值
     *
     * 2、确定递归终止条件
     * root == null, return
     *
     * 3、确定单层递归逻辑
     * 中序遍历，具体逻辑如上
     *
     * T:O(n)
     * S:O(n)
     */
    private int count;
    private int maxCount;
    private TreeNode prev = null;
    private List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        traversal(root);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);

        if (prev == null) {
            count = 1;
        } else if (prev.val == root.val) {
            count++;
        } else {
            count = 1;
        }
        prev = root;

        if (count == maxCount) {
            list.add(root.val);
        } else if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(root.val);
        }

        traversal(root.right);
    }
}
