package tree;

/**
 * 左叶子之和：
 *
 * 计算给定二叉树的所有左叶子之和
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {

    }

    private int sumOfLeftLeaves1(TreeNode root) {
        return sumLeftLeaves(root, false);
    }

    private int sumLeftLeaves(TreeNode node, boolean isLeftLeave) {
        if (node == null) return 0;

        int leaves = 0;
        // 如果是叶子节点并且是左节点
        if (isLeftLeave && node.left == null && node.right == null) {
            leaves = node.val;
        }

        int left = sumLeftLeaves(node.left, true);
        int right = sumLeftLeaves(node.right, false);
        return leaves + left + right;
    }

    // 个人实现
    private int result;
    private int sumOfLeftLeaves2(TreeNode root) {
        recursion(root, false);
        return result;
    }

    private void recursion(TreeNode node, boolean isLeft) {
        if (node == null) return;
        // 如果是叶子节点并且是左节点，说明是左叶子节点
        if (node.left == null && node.right == null && isLeft) {
            result += node.val;
        }
        recursion(node.left, true);
        recursion(node.right, false);
    }

    // 扩展：获取所有节点的和
    private int sumAllNode(TreeNode root) {
        if (root == null) return 0;
        int left = sumAllNode(root.left);
        int right = sumAllNode(root.right);
        return left + right;
    }

    // 扩展：获取所有叶子节点之和
    private int sumAllLeaves(TreeNode root) {
        if (root == null) return 0;
        int leave = 0;
        if (root.left == null && root.right == null) {
            leave = root.val;
        }

        int left = sumAllLeaves(root.left);
        int right = sumAllLeaves(root.right);
        return leave + left + right;
    }
}
