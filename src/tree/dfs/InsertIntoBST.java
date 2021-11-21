package tree.dfs;

import tree.TreeNode;

/**
 * 701.二叉搜索树中的插入操作
 *
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 * 示例1：
 *
 *          4                4
 *         / \             /   \
 *        2   7           2     7
 *       / \             / \   /
 *      1   3           1   3 5
 *
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 */
public class InsertIntoBST {

    /**
     * 题目中提示存在多种有效的插入方式会容易让我们认为需要重构二叉搜索树，其实并不需要
     * 根据二叉搜索树有序的特性，只需要找到能插入的节点添加后，将节点返回即可
     *
     * 1、确定递归函数参数和返回值
     * 参数需要根节点和待插入的节点数值
     * 因为最终要返回的结果是根节点，返回值为新增的节点 TreeNode
     *
     * 2、确定递归终止条件
     * root == null, return newNode
     *
     * 3、确定单层递归逻辑
     * 二叉搜索树时有序的，所以
     * root.val < val, 走右子树 root.right = traversal(root.right, val)
     * root.val > val, 走左子树 root.left = traversal(root.left, val)
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // 将新增的节点返回给父节点
            return new TreeNode(val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}
