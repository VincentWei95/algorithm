package tree;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 扩展1：
     *
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
     *
     * 给定如下二叉树，以及目标和 sum = 22
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     *
     *  [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     *
     * dfs遍历，遍历每个节点都存进临时列表nodeList，直到叶子节点判断sum是否为0，有则说明是一条符合路径添加到result列表，
     * 不是则继续遍历，nodeList将刚添加的节点移除
     *
     * T:O(n)
     * S:O(n)
     */
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> nodeList = new ArrayList<>();
    private List<List<Integer>> pathSum1(TreeNode root, int sum) {
        if (root == null) return result;

        sum -= root.val;
        nodeList.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                result.add(new ArrayList<>(nodeList));
            }
        }
        pathSum1(root.left, sum);
        pathSum1(root.right, sum);
        nodeList.remove(nodeList.size() - 1);
        return result;
    }

    /**
     * 扩展2：
     *
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数
     *
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * 返回 3。和等于 8 的路径有:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     *
     * T:O(n)
     * S:O(n)
     */
    private int pathSize;
    private int pathSum2(TreeNode root, int sum) {
        if (root == null) return 0;
        helper(root.left, sum);
        helper(root.right, sum);
        return pathSize;
    }

    private void helper(TreeNode root, int sum) {
        if (root == null) return;

        sum -= root.val;
        if (sum == 0) {
            pathSize++;
        } else {
            helper(root.left, sum);
            helper(root.right, sum);
        }
    }
}
