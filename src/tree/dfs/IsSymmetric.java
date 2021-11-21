package tree.dfs;

import tree.TreeNode;

/**
 * 101.对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class IsSymmetric {

    /**
     * 1、确定递归参数和返回值
     * 题目并不是对比的左右节点，而是对比的左右子树，所以需要两个参数
     * 最终返回值 boolean
     *
     * 2、递归终止条件
     * 需要区分几种情况：
     * （1）左节点为空右节点不为空，返回 false
     * （2）左节点不为空右节点为空，返回 false
     * （3）左节点和右节点都为空，返回 true
     * （4）当左右节点都不为空时，左节点的数值和右节点的数值相同，返回 true，否则 false
     *
     * 3、单层递归逻辑
     * 当左节点和右节点都不为空时，左子树的左节点和右子树的右节点对比，左子树的右节点和右子树的左节点对比
     */
    public boolean isSymmetric(TreeNode root) {
        return recursive(root.left, root.right);
    }

    private boolean recursive(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p == null && q == null) return true;
        if (p.val != q.val) return false;

        boolean outside = recursive(p.left, q.right);
        boolean inside = recursive(p.right, q.left);
        return outside && inside;
    }
}
