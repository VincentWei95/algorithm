package tree.dfs;

import tree.TreeNode;

/**
 * 236.二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 示例1：
 *
 *               3
 *             /   \
 *            5     1
 *           / \   / \
 *          6   2 0  8
 *             / \
 *            7   4
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 * 示例2：
 *
 *              3
 *            /   \
 *           5     1
 *          / \   / \
 *         6   2 0   8
 *            / \
 *           7   4
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 */
public class LowestCommonAncestor {

    /**
     * 该题目需要用到回溯、后序遍历自底向上查找
     *
     * 如何判断一个节点是节点 p 和节点 q 的公共祖先呢？
     * 如果找到一个节点，发现左子树出现节点 p，右子树出现节点 q，或者左子树出现节点 q，右子树出现节点 p，那么该节点就是它们的最近公共祖先
     *
     * 1、确定递归函数参数和返回值
     * 参数需要二叉树根节点、节点 p 和节点 q
     * 返回值是找到的节点 TreeNode
     *
     * 2、确定递归终止条件
     * 如果遇到了节点 p、节点 q 或 空节点则返回
     *
     * 3、确定单层递归逻辑
     * 值得注意的是，本题函数有返回值，是因为回溯过程需要递归函数的返回值做判断，但本题依然要遍历树的所有节点
     * 如果递归函数有返回值，如何区分要搜索一条边，还是搜索整棵树呢？
     *
     * 搜索一条边的写法：
     *
     * if (traversal(root.left)) return;
     * if (traversal(root.right)) return;
     *
     * 搜索整棵树的写法：
     *
     * left = traversal(root.left);
     * right = traversal(root.right);
     * left 与 right 的逻辑处理
     *
     * 在递归函数有返回值的情况下：
     * 如果要搜索一条边，递归函数返回值不为空的时候，立刻返回
     * 如果搜索整棵树，直接用一个变量 left、right 接住返回值，left、right 后续的逻辑处理就是后序遍历中处理中间节点的逻辑（也是回溯）
     *
     * 所以逻辑如下：
     *
     * TreeNode left = traversal(root.left, p, q);
     * TreeNode right = traversal(root.right, p, q);
     *
     * 如果 left 和 right 都不为空，说明此时 root 就是最近公共节点
     * 如果 left 为空 right 不为空，就返回 right，说明目标节点是通过 right 返回的；反之亦然
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return traversal(root, p, q);
    }

    private TreeNode traversal(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = traversal(root.left, p, q);
        TreeNode right = traversal(root.right, p, q);

        // left 和 right 都不为空，root 是最近公共祖先
        if (left != null && right != null) return root;
        // left 为空 right 不为空，最近公共祖先节点是从 right 返回的
        if (left == null && right != null) return right;
        // left 不为空 right 为空，最近公共祖先节点是从 left 返回的
        if (left != null && right == null) return left;
        return null;
    }
}
