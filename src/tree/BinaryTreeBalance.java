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
     * T:最好情况是O(nlogn)，但自顶向下会存在重复计算的问题，最差情况是O(n2)
     * S:O(n)
     */
    private boolean isTreeBalanceTopDown(TreeNode root) {
        if (root == null) return true;

        // 计算左子树和右子树的高度
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        // 二叉平衡树的任何一棵子树也都必须是二叉平衡树
        // Math.abs(left - right) <= 1 只是计算了根节点的两个子节点
        // isTreeBalanceTopDown(root.left)和isTeeBalanceTopDown(root.right)计算其他子节点也要满足平衡
        return Math.abs(left - right) <= 1 &&
                isTreeBalanceTopDown(root.left) && isTreeBalanceTopDown(root.right);
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1; // Math.max(left, right) 选择最深的节点作为高度
    }

    /**
     * 方式2：从底部到根节点递归访问
     *
     * T:O(n) 自底向上类似于后序遍历，不存在重复计算问题
     * S:O(n)
     */
    private boolean isTreeBalanceDownTop(TreeNode root) {
        return getHeightAndCheck(root) != -1;
    }

    private int getHeightAndCheck(TreeNode root) {
        if (root == null) return 0;

        // 有一个节点不满足平衡即为-1，就不是平衡二叉树，直接返回不平衡

        // 左子节点不是平衡二叉树
        int left = getHeightAndCheck(root.left);
        if (left == -1) return -1;

        // 右子节点不是平衡二叉树
        int right = getHeightAndCheck(root.right);
        if (right == -1) return -1;

        // 左右子节点都不是平衡二叉树
        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }


}
