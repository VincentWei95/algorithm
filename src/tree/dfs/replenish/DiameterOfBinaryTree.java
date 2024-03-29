package tree.dfs.replenish;

import tree.TreeNode;

/**
 * 543.二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {

    /**
     * 直径长度定义：二叉树直径长度=树中任意两节点最短路径的最大值
     *
     * 路径长度定义：两节点之间的路径长度=它们之间边的数目
     *
     * 如何求得两个叶子节点之间的路径长度：
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 以 a -> b 表示从叶子节点 a 到叶子节点 b 的路径长度
     * 4->5 = 2->4 + 2->5  求4到5的路径长度是 2到4的长度+ 2到5的路径长度
     * 4->3 = 1->4 + 1->3  求4到3的路径长度是 1到4的长度+ 1到3的路径长度
     *
     * 根据上面的分析，两个叶子节点之间的路径=根节点左右儿子的深度之和
     *
     * 但是还有一个条件需要注意：不一定经过根节点
     *
     *          1
     *         /
     *        2
     *       / \
     *      4   5
     *     /     \
     *    6       3
     * 上面的树最大直径是 6->4->2->5->3，没有经过根节点才是最大直径。所以可以提供一个变量不断更新最大直径
     *
     * T:O(n)
     * S:O(height)
     */
    private int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        ans = Math.max(ans, left + right); // 计算左儿子和右儿子深度之和，考虑可能不经过根节点，所以需要获取最大值
        return Math.max(left, right) + 1; // 返回该节点为根的子树的深度
    }
}
