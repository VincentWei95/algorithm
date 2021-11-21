package tree.dfs;

import tree.TreeNode;

/**
 * 617.合并二叉树
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例1:
 *
 * 输入:
 *
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 *
 * 输出:
 *
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 * 注意:合并必须从两个树的根节点开始。
 */
public class MergeTrees {

    /**
     * 该题目可以使用前/中/后序遍历都可实现
     *
     * 1、确定递归函数参数和返回值
     * 递归合并两棵树，只需要两棵树作为参数即可
     * 返回值为新的二叉树 TreeNode
     *
     * 2、确定递归终止条件
     * 当 root1 为 null 时，返回 root2
     * 当 root2 为 null 时，返回 root1
     *
     * 3、确定单层递归逻辑
     * 当 root1 和 root2 都不为空时，root1.val + root2.val 作为根节点，然后继续递归遍历两棵树的左节点和右节点
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }
}
