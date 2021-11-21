package tree.dfs.replenish;

import tree.TreeNode;

/**
 * 938.二叉搜索树的范围和：
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
public class RangeSumBST {

    public static void main(String[] args) {

    }

    /**
     * 1、root节点为空，返回0
     * 2、root节点的值大于high：由于二叉搜索树右子树上所有节点的值均大于根节点的值，即均大于high，故无需考虑右子树，返回左子树的范围和
     * 3、root节点的值小于low：由于二叉搜索树左子树上所有节点的值均小于根节点的值，即均小于low，故无需考虑左子树，返回右子树的范围和
     * 4、root节点的值在[low,high]内：此时返回root节点的值、左子树的范围和、右子树的范围和三者之和
     *
     * T:O(n)
     * S:O(n)
     */
    private int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}
