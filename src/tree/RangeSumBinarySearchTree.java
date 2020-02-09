package tree;

/**
 * 二叉搜索树的范围和：
 *
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 *
 * 二叉搜索树保证具有唯一的值
 *
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 */
public class RangeSumBinarySearchTree {

    public static void main(String[] args) {

    }

    private int result = 0;

    private int rangeSumBST(TreeNode root, int L, int R) {
        result = 0;
        dfs(root, L, R);
        return result;
    }

    private void dfs(TreeNode node, int L, int R) {
        if (node != null) {
            // 在[L, R]范围内，添加值继续dfs
            if (node.val >= L && node.val <= R) {
                result += node.val;
                dfs(node.left, L, R);
                dfs(node.right, L, R);
            } else if (node.val < L) {
                dfs(node.right, L, R);
            } else if (node.val > R) {
                dfs(node.left, L, R);
            }
        }
    }
}
