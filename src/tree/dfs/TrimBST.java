package tree.dfs;

import tree.TreeNode;

/**
 * 669.修建二叉搜索树
 *
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 * 示例1：
 *
 *          1           1
 *         / \   ->      \
 *        0   2           2
 *
 * 输入：root = [1,0,2], low = 1, high = 2
 * 输出：[1,null,2]
 *
 * 示例2：
 *
 *          3               3
 *         / \             /
 *        0   4           2
 *         \       ->    /
 *          2           1
 *         /
 *        1
 *
 * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * 输出：[3,2,null,1]
 */
public class TrimBST {

    /**
     * 1、确定递归函数参数和返回值
     * 参数需要根节点和限定的范围数值
     * 这道题目虽然是需要搜索整棵树，但是有返回值会更好处理可以直接对接删除后的子树，返回值为 TreeNode
     *
     * 2、确定递归终止条件
     * root == null, return null
     *
     * 3、确定单层递归逻辑
     * root.val < low，递归右子树补位
     * root.val > high，递归左子树补位
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
       if (root == null) return null;

       // 修剪的二叉搜索树节点数值范围需要在[low, high]
       // 小于最小限定范围的节点，让右子树补位
       if (root.val < low) {
           return trimBST(root.right, low, high);
       }
       // 大于最大限定范围的节点，让左子树补位
       if (root.val > high) {
           return trimBST(root.left, low, high);
       }

       root.left = trimBST(root.left, low, high); // 接入符合条件的左子树
       root.right = trimBST(root.right, low, high); // 接入符合条件的右子树
       return root;
    }
}
