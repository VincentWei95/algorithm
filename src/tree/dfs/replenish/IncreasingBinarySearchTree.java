package tree.dfs.replenish;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 897.递增顺序搜索树：
 *
 * 给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点
 *
 * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
 *
 *        5
 *       / \
 *     3    6
 *    / \    \
 *   2   4    8
 *  /        / \
 * 1        7   9
 *
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *            \
 *             7
 *              \
 *               8
 *                \
 *                 9
 */
public class IncreasingBinarySearchTree {

    public static void main(String[] args) {

    }

    /**
     * 方式1：中序遍历+遍历列表
     *
     * 中序遍历可以获取到树的顺序节点排列，可以先通过中序遍历使用列表存储，然后遍历列表构建新的二叉搜索树
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode increasingBST(TreeNode root) {
        List<Integer> nodeList = new ArrayList<>();
        inOrder(root, nodeList);
        TreeNode newRoot = new TreeNode(nodeList.get(0));
        TreeNode node = newRoot;
        for (int i = 1; i < nodeList.size(); i++) {
            TreeNode newNode = new TreeNode(nodeList.get(i));
            node.right = newNode;
            node = newNode;
        }
        return newRoot;
    }

    private void inOrder(TreeNode root, List<Integer> nodeList) {
        if (root == null) return;

        inOrder(root.left, nodeList);
        nodeList.add(root.val);
        inOrder(root.right, nodeList);
    }

    /**
     * 方式2：中序遍历+更改树节点
     *
     * 在中序遍历的时候，将当前节点的左节点置为null，修改为当前节点的右节点，中序遍历结束，树节点结构也更改完成
     *
     * T:O(n)
     * S:O(1)
     */

    TreeNode cur;
    private TreeNode increasingBST2(TreeNode root) {
        TreeNode newRoot = new TreeNode(0);
        cur = newRoot;
        inOrder(root);
        return newRoot.right;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);
        cur.right = root;
        root.left = null;
        cur = root;
        inOrder(root.right);
    }
}
