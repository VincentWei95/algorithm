package tree.dfs;

import tree.TreeNode;

/**
 * 110.平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 示例1：
 *
 *          3
 *         / \
 *        9  20
 *          / \
 *         15  7
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例2：
 *
 *                 1
 *                / \
 *               2   2
 *              / \
 *             3   3
 *            / \
 *           4   4
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例3：
 *
 * 输入：root = []
 * 输出：true
 */
public class IsBalanced {

    /**
     * 思路：
     * 因为是求的高度差，一般我们说高度都是从下到上计算，可以明确使用的后序遍历
     * 所以要求高度差，首先就需要知道左右子树的高度，通过左右子树的高度差绝对值判断如果超过1则二叉树不平衡
     *
     * 1、确定递归函数参数和返回值
     * 传入节点指针，就没有其他参数需要传递了，返回值要返回传入节点为根节点树的深度
     * 如何标记左右子树是否差值大于1呢？
     * 如果当前传入节点为根节点的二叉树已经不是二叉平衡树了，返回高度没有意义，返回-1标记
     *
     * 2、确定终止条件
     * 递归过程中遇到空节点返回0，表示当前节点为根节点的树高度为0
     *
     * 3、确定单层递归逻辑
     * 分别求出左右子树的高度，如果差值 <= 1，则返回当前二叉树的高度
     * 否则返回-1表示已经不符合条件
     *
     * T:O(n)
     * S:O(n)
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;

        // 后序遍历，分别先求出左右子树的高度
        int leftDepth = getDepth(root.left);
        if (leftDepth == -1) return -1;

        int rightDepth = getDepth(root.right);
        if (rightDepth == -1) return -1;

        // 高度差已经超过1，不是平衡二叉树直接返回-1
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        // 高度差没超过1，返回以当前节点为根节点的最大高度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
