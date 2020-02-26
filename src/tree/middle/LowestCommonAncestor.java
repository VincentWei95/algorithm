package tree.middle;

import tree.TreeNode;

/**
 * 二叉树的最近公共祖先（和二叉搜索树的最近公共祖先区分）：
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    /**
     * 回溯：
     *
     * 1、从根节点开始遍历树。
     * 2、如果当前节点本身是 p 或 q 中的一个，我们会将变量 mid 标记为 true，并继续搜索左右分支中的另一个节点。
     * 3、如果左分支或右分支中的任何一个返回 true，则表示在下面找到了两个节点中的一个。
     * 4、如果在遍历的任何点上，左、右或中三个标志中的任意两个变为 true，这意味着我们找到了节点 p 和 q 的最近公共祖先。
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode result;
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recursion(root, p, q);
        return result;
    }

    private boolean recursion(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;

        // 遍历左右子树
        int left = recursion(node.left, p, q) ? 1 : 0;
        int right = recursion(node.right, p, q) ? 1 : 0;

        // 如果找到节点p或q，定义为true
        int mid = (node == p || node == q) ? 1 : 0;

        // 如果p和q都找到了，也就找到了最近公共祖先
        if (mid + left + right >= 2) {
            result = node;
        }

        return mid + left + right > 0;
    }
}
