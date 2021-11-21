package tree.dfs;

import tree.TreeNode;

/**
 * 235.二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树: root =[6,2,8,0,4,7,9,null,null,3,5]
 *
 *               6
 *             /   \
 *            2     8
 *           / \   / \
 *          0   4 7   9
 *             / \
 *            3   5
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例2：
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 */
public class LowestCommonAncestorBST {

    /**
     * 因为二叉搜索树是有序的特性，当找到节点在 [p, q] 之间时，它就是两个节点的公共祖先
     *
     * 1、确定递归函数参数和返回值
     * 参数需要根节点、节点 p 和节点 q
     * 返回值是找到的公共祖先节点 TreeNode
     *
     * 2、确定递归终止条件
     * root == null, return null
     * root.val >= p.val && root.val <= q.val 或 root.val >= q.val && root.val <= p.val, return root
     *
     * 3、确定单层递归逻辑
     * 因为二叉搜索树是有序的，所以可以对比数值有方向的查找。不需要遍历整棵树，找到节点就直接返回
     * root.val > p.val && root.val > q.val，走左子树继续遍历
     * root.val < q.val && root.val < q.val，走右子树继续遍历
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
