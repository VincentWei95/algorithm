package tree;

/**
 * 二叉搜索树的最小绝对差
 *
 *给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 输入：
 *      1
 *       \
 *        3
 *       /
 *      2
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）
 *
 * 提示：
 * 树中至少有2个节点
 */
public class MinimumDifference {

    public static void main(String[] args) {

    }

    /**
     * 二叉搜索树的中序遍历可以获取到递增的序列
     *
     * T:O(n)
     * S:O(n)
     */
    private int prev = -1;
    private int ans = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        if (prev == -1) {
            prev = root.val;
        } else {
            ans = Math.min(ans, root.val - prev);
            prev = root.val;
        }
        dfs(root.right);
    }
}
