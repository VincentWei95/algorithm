package tree;

/**
 * 判断二叉树是否平衡：
 *
 * 给你一棵二叉树，你要判断它是否平衡。这里平衡指的是，对于树上任意一个节点，它的两棵子树的高度差不能大于 1
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 它的任意节点的左右子树高度差都不大于 1，因此它是一棵平衡二叉树
 *
 * 再比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *          \
 *           8
 *            \
 *            16
 *
 * 在这棵树中，根节点的左右子树高度差为 2，因此它不是一棵平衡二叉树
 */
public class BinaryTreeBalance {

    public static void main(String[] args) {

    }

    /**
     * 方式1：从根节点到底部递归访问
     *
     * T:O(nlogn)
     * S:O(n)
     */
    private boolean isTreeBalanceTopDown(TreeNode root) {
        if (root == null) return true;
        return Math.abs(getHeight(root.leftNode)- getHeight(root.rightNode)) <= 1 &&
                isTreeBalanceTopDown(root.leftNode) && isTreeBalanceTopDown(root.rightNode);
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.leftNode);
        int right = getHeight(root.rightNode);
        return Math.max(left, right) + 1;
    }

    /**
     * 方式2：从底部到根节点递归访问
     *
     * T:O(n)
     * S:O(n)
     */
    private boolean isTreeBalanceDownTop(TreeNode root) {
        return getHeightAndCheck(root) != -1;
    }

    private int getHeightAndCheck(TreeNode root) {
        if (root == null) return 0;

        int left = getHeightAndCheck(root.leftNode);
        if (left == -1) return -1;

        int right = getHeightAndCheck(root.rightNode);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }


}
