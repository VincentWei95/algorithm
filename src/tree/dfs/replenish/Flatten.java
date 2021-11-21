package tree.dfs.replenish;

import tree.TreeNode;

/**
 * 114.二叉树展开为链表：
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class Flatten {

    public static void main(String[] args) {

    }

    /**
     * 方式1：循环遍历法
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * 将 1 的左子树插入到右子树的地方
     *     1
     *      \
     *       2         5
     *      / \         \
     *     3   4         6
     * 将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *      / \
     *     3   4
     *          \
     *           5
     *            \
     *             6
     *
     * 将 2 的左子树插入到右子树的地方
     *     1
     *      \
     *       2
     *        \
     *         3       4
     *                  \
     *                   5
     *                    \
     *                     6
     *
     *  将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *        \
     *         3
     *          \
     *           4
     *            \
     *             5
     *              \
     *               6
     */
    private void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                // 找到左子树最右边的节点
                TreeNode prev = root.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                // 将原来的右子树接到左子树的最右边的节点
                prev.right = root.right;
                // 将左子树接入到右子树
                root.right = root.left;
                root.left = null;
                // 往接入最新的右子树移动
                root = root.right;
            }
        }
    }
}
