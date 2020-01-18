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
        TreeNode tmp = root.leftNode;
        root.leftNode = root.rightNode;
        root.rightNode = tmp;
        invertBinaryTreeRecursive(root.leftNode);
        invertBinaryTreeRecursive(root.rightNode);
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
            TreeNode tmp = node.leftNode;
            node.leftNode = node.rightNode;
            node.rightNode = tmp;
            if (node.leftNode != null) queue.add(node.leftNode);
            if (node.rightNode != null) queue.add(node.rightNode);
        }
        return root;
    }
}
