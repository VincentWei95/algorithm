package tree.dfs;

import tree.TreeNode;

/**
 * 112.路径总和
 *
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例1：
 *
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \      \
 *        7   2       1
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例2：
 *
 *          1
 *         / \
 *        2   3
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 */
public class HasPathSum {

    /**
     * 1、确定递归函数参数和返回值
     * 题目会用到前序遍历递归和回溯需要 TreeNode
     * 统计是否有路径可以在遍历节点时 targetSum - root.val，需要参数 targetSum
     * 递归函数只需要确认有符合的路径就返回，需要有返回值为 boolean
     *
     * 2、确定递归终止条件
     * 当到达叶子节点且 targetSum == 0 时，说明有符合的路径返回 true，否则 false
     *
     * 3、确定单层递归逻辑
     * 因为终止条件是判断叶子节点，所以递归过程中就不要让空节点进入递归了
     * 递归函数是有返回值的，如果递归返回 true，说明找到了合适的路径，应该立即返回
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return traversal(root, targetSum);
    }

    private boolean traversal(TreeNode root, int targetSum) {
        targetSum -= root.val;
        // 到达叶子节点
        if (root.left == null && root.right == null) {
            // 如果为0说明找到路径返回
            return targetSum == 0;
        }

        if (root.left != null) {
            boolean found = traversal(root.left, targetSum);
            if (found) return true;
        }
        if (root.right != null) {
            boolean found = traversal(root.right, targetSum);
            if (found) return true;
        }
        return false;
    }
}
