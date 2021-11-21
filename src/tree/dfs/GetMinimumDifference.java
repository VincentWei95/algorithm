package tree.dfs;

import tree.TreeNode;

/**
 * 530.二叉搜索树的最小绝对差
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值
 *
 * 示例1：
 *
 *              4
 *             / \
 *            2   6
 *           / \
 *          1   3
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *
 * 示例2：
 *
 *              1
 *             / \
 *            0  48
 *              /  \
 *             12  49
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 */
public class GetMinimumDifference {

    /**
     * 根据二叉搜索树有序的特性，可以通过中序遍历，用当前节点 - 上一个节点，求最小差值
     *
     * 1、确定递归函数参数和返回值
     * 参数需要根节点
     * 该题目需要遍历整棵树，所以不需要返回值 void
     *
     * 2、确定递归终止条件
     * root == null, return
     *
     * 3、确定单层递归逻辑
     * 中序遍历时，记录上一个节点，求最小差值
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode prev = null;
    private int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        traversal(root);
        return min;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.left);
        if (prev != null) {
            min = Math.min(min, root.val - prev.val);
        }
        prev = root;
        traversal(root.right);
    }
}
