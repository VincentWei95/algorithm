package tree.middle;

import tree.TreeNode;

/**
 * 二叉树展开为链表：
 *
 * 例如，给定二叉树
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
