package tree.dfs;

import tree.TreeNode;

/**
 * 450.删除二叉搜索树中的节点
 *
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 * 示例1：
 *
 *          5               5
 *         / \             / \
 *        3   6   ->      4   6
 *       / \   \         /     \
 *      2   4   7       2       7
 *
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *          5
 *         / \
 *        2   6
 *         \   \
 *          4   7
 */
public class DeleteNode {

    /**
     * 1、确定递归函数参数和返回值
     * 参数需要根节点和查找的待删除的数值
     * 最终需要返回根节点，返回值 TreeNode
     *
     * 2、确定递归终止条件
     * root == null, return null
     * root.val == key, return result
     *
     * 3、确定单层递归逻辑
     * 查找到待删除的节点 root.val == key 时，需要分以下几种情况：
     * [1] root 是叶子节点即 root.left == null && root.right == null, return null
     * [2] root 左节点不为空右节点为空即 root.left != null && root.right == null, 左节点补位 return root.left
     * [3] root 左节点为空右节点不为空即 root.left == null && root.right != null, 右节点补位 return root.right
     * [4] root 左右节点都不为空即 root.left != null && root.right != null
     * 因为是二叉搜索树，所以需要找到左子树的后继节点，可以查找右子树最左边的节点，就是待删除节点的左子树的后继节点，此时补位 root.right
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;

            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            cur.left = root.left;
            root = root.right;
            return root;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
}
