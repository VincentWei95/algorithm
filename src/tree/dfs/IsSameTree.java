package tree.dfs;

import tree.TreeNode;

/**
 * 100.相同的树
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例1：
 *
 *          1       1
 *         / \     / \
 *        2  3    2  3
 *
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 *
 * 示例2：
 *
 *      1   1
 *     /     \
 *    2       2
 *
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 *
 * 示例3：
 *
 *      1       1
 *     / \     / \
 *    2   1   1   2
 *
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 */
public class IsSameTree {

    /**
     * 1、确定递归函数参数和返回值
     * 题目需要对比两颗树，所以需要两个参数，返回值 boolean
     *
     * 2、确定递归终止条件
     * （1）p == null && q != null, return false
     * （2）p != null && q == null, return false
     * （3）p == null && q == null, return true
     * （4）p != null && q != null, p.val == q.val, return true; p.val != q.val, return false
     *
     * 3、单层递归逻辑
     * 在两颗树的节点都不为空即 p != null && q != null 的情况
     * 需要对比 p 的左节点和 q 的左节点，p 的右节点和 q 的右节点相同，使用前序遍历
     *
     * T:O(n)
     * S:O(n)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p == null && q == null) return true;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
