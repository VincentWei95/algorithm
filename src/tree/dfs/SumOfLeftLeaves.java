package tree.dfs;

import tree.TreeNode;

/**
 * 404.左叶子之和
 *
 * 计算给定二叉树的所有左叶子之和
 *
 *          3
 *         / \
 *        9  20
 *          /  \
 *        15    7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15， 所以返回 24
 */
public class SumOfLeftLeaves {

    /**
     * 怎么确定是左叶子？通过当前节点是无法知道是否是左叶子节点的，而是需要通过父节点确认，所以这道题目不能一上来就想用层序遍历
     * 左叶子节点：root.left != null && root.left.left == null && root.left.right == null
     *
     * 1、确定递归函数参数和返回值
     * 使用后序遍历并且需要确认是否为左叶子，需要参数 TreeNode，返回值 int
     *
     * 2、确定递归终止条件
     * root == null, return 0
     *
     * 3、确定单层递归逻辑
     * 后序遍历，当遇到左叶子节点的时候，记录数值，然后通过递归求左子树左叶子之和，和右子树左叶子之和，相加便是左叶子之和
     *
     * T:O(n)
     * S:O(n)
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int leftTreeValue = sumOfLeftLeaves(root.left);
        int rightTreeValue = sumOfLeftLeaves(root.right);

        int leaveValue = 0;
        // 左叶子
        if (root.left != null && root.left.left == null && root.left.right == null) {
            leaveValue = root.left.val;
        }
        return leaveValue + leftTreeValue + rightTreeValue;
    }
}
