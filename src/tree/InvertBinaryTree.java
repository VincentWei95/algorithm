package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树：
 *
 * 给你一棵二叉树，你要把它左右镜像翻转，然后返回翻转后的二叉树
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 左右翻转后的二叉树是：
 *
 *       1
 *     /   \
 *    4     2
 *   / \
 *  16  8
 *
 *  我们可以看到，二叉树上所有节点都沿中轴线左右互换了位置
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归实现
     *
     * 不断交换每个节点的左右两个节点即可
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode invertBinaryTreeRecursive(TreeNode root) {
        if (root == null) return root;
        // 使用的前序遍历
        // 前序遍历是先打印自身，再打印左子树，然后打印右子树
        // 遇到节点就交换左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertBinaryTreeRecursive(root.left);
        invertBinaryTreeRecursive(root.right);
        return root;
    }

    /**
     * 方式2：辅助队列实现
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode invertBinaryTreeIterative(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return root;
    }
}
