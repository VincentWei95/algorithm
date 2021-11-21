package tree.dfs;

import tree.TreeNode;

/**
 * 98.验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例1：
 *
 *              2
 *             / \
 *            1   3
 *
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例2：
 *
 *              5
 *             / \
 *            1   4
 *               / \
 *              3   6
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */
public class IsValidBST {

    /**
     * 因为二叉搜索树的中序遍历是有序的，所以如果上一个节点 >= 当前节点，说明不是二叉搜索树
     * 上一个节点可以用变量 TreeNode 记录
     *
     * 1、确定递归函数参数和返回值
     * 参数只需要传递根节点遍历即可
     * 因为不需要遍历整棵树符合条件就返回，所以返回值为 boolean
     *
     * 2、确定递归终止条件
     * 当 root == null, return true
     * 当 prev.val >= root.val, return false
     *
     * 3、确定单层递归逻辑
     * 中序遍历二叉搜索树，因为返回值是 boolean，会出现如下情况：
     * boolean left = traversal(root.left);
     * boolean right = traversal(root.right);
     * 所以需要同时判断 left && right 作为返回值
     */
    private TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        boolean left = isValidBST(root.left);

        if (prev != null && prev.val >= root.val) return false;
        prev = root;

        boolean right = isValidBST(root.right);

        return left && right;
    }
}
