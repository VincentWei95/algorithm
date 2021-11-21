package tree.dfs;

import tree.TreeNode;

/**
 * 572.另一颗树的子树
 *
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * 示例1：
 *
 *          root
 *           3              subRoot
 *         /  \                4
 *        4    5              / \
 *       / \                 1   2
 *      1   2
 *
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 *
 * 示例2：
 *
 *          root
 *           3              subRoot
 *         /  \                4
 *        4    5              / \
 *       / \                 1   2
 *      1   2
 *         /
 *        0
 *
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 */
public class IsSubtree {

    /**
     * 思路：
     * root 前序遍历，当找到节点 root.val == subRoot.val 时，对比这两颗子树是否相同
     *
     * 1、确定递归函数参数和返回值
     * 需要两个递归函数，一个是 root 前序遍历的递归，一个是对比两颗子树是否相同的递归，都需要两个 TreeNode 参数
     * 为了方便判断，root 前序遍历的递归不需要返回值，用一个变量记录是否找到子树
     * 第二个递归返回是否相同需要返回值 boolean
     *
     * 2、确定递归终止条件
     * root 前序遍历递归终止条件为 root == null
     *
     * 判断两颗子树是否相同递归终止条件：
     * （1）p == null && q != null, return false
     * （2）p != null && q == null, return false
     * （3）p == null && q == null, return true
     * （4）p != null && q != null, p.val == q.val, return true; p.val != q.val, return false
     *
     * 3、确定单层递归逻辑
     * root 前序遍历只需要遍历完整棵树即可
     *
     * 判断两颗子树是否相同，在 p != null && q != null 情况下
     * p 的左子树和 q 的左子树相同，p 的右子树和 q 的右子树相同
     */
    private boolean isSubTree = false;
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        dfs(root, subRoot);
        return isSubTree;
    }

    private void dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) return;

        if (root.val == subRoot.val) {
            // 已经找到即使又找到相同的节点，也不需要再对比了
            if (!isSubTree) {
                isSubTree = isSameTree(root, subRoot);
            }
        }
        // 已经找到符合的子树，不需要再前序遍历直接返回
        if (isSubTree) return;

        dfs(root.left, subRoot);
        dfs(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p == null && q == null) return true;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
