package tree.dfs;

import tree.TreeNode;

/**
 * 700.二叉搜索树中的搜索
 *
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 *
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class SearchBST {

    /**
     * 1、确定递归函数参数和返回值
     * 传入根节点和搜索的数值，返回找到的节点
     *
     * 2、确定递归终止条件
     * root == null, return null
     * root.val == val, return root
     *
     * 3、确定单层递归逻辑
     * 因为二叉搜索树是有序的，所以可以有方向的去搜索
     * 当 root.val > val，搜索左子树
     * 当 root.val < val，搜索右子树
     * 否则返回 null
     *
     * 递归是否需要返回值，这道题目不需要递归整棵树而且满足条件就要立刻返回，所以递归需要有返回值
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;

        if (root.val > val) return searchBST(root.left, val);
        if (root.val < val) return searchBST(root.right, val);
        return null;
    }
}
