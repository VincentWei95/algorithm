package tree.dfs;

import tree.TreeNode;

/**
 * 226.翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertTree {

    /**
     * 按递归方法论三个步骤：
     *
     * 1、确定递归的参数和返回值
     * 只需要操作二叉树翻转，所以参数只有 root，不需要返回值 void
     *
     * 2、递归终止条件
     * 到达叶子结点 root == null
     *
     * 3、单层递归逻辑
     * 每个节点都交换左右节点，可以用前序遍历（从上到下交换）或后序遍历（从下到上交换），但不能用中序遍历
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
