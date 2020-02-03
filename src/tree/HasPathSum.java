package tree;

import java.util.Stack;

/**
 * 路径总和：
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
 *
 * 给定如下二叉树，以及目标和 sum = 22
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 *
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2
 */
public class HasPathSum {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归
     *
     * 遍历整棵树从根节点到叶子节点，当访问到叶子节点时判断sum是否为0即可
     *
     * T:O(n) 最坏情况整棵树是非平衡
     * T:O(logN) 树平衡
     * S:O(n)
     */
    private boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null) return false;

        sum -= root.val;
        // 如果已经是叶子节点
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSumRecursive(root.left, sum) || hasPathSumRecursive(root.right, sum);
    }

    /**
     * 方式2：栈
     *
     * T:O(n) 最坏情况整棵树是非平衡
     * T:O(logN) 树平衡
     * S:O(n)
     */
    private boolean hasPathSumStack(TreeNode root, int sum) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(sum - root.val);

        int currSum;
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            currSum = sumStack.pop();

            // 如果访问到了叶子节点
            if (node.left == null && node.right == null && currSum == 0) return true;

            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(currSum - node.left.val);
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(currSum - node.right.val);
            }
        }

        return false;
    }
}
