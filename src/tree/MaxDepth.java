package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度：
 *
 * 给你一棵二叉树，你要找到从根节点到最远叶子节点的深度
 *
 * 比如说，给你的二叉树是
 *
 *     1
 *   /   \
 *  2     4
 *       / \
 *      8  16
 *
 * 这棵树有 3 个叶子节点，分别是 2，8，16。最远的叶子节点是 8 和 16，根节点到 8 或 16 都有 3 个节点，因此最大深度是 3
 */
public class MaxDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.leftNode = new TreeNode(2);
        root.rightNode = new TreeNode(4);
        root.rightNode.leftNode = new TreeNode(8);
        root.rightNode.rightNode = new TreeNode(16);

        MaxDepth main = new MaxDepth();
        System.out.println(main.maxDepthIterative(root));
    }

    /**
     * 方式1：递归实现
     *
     * T:O(n) S:O(n)
     */
    private int maxDepthRecursive(TreeNode root) {
        if (root == null) return 0;
        if (root.leftNode == null && root.rightNode == null) return 1; // 只有根节点
        if (root.leftNode == null) return maxDepthRecursive(root.rightNode) + 1; // 如果没有左节点，遍历右节点，1为根节点
        if (root.rightNode == null) return maxDepthRecursive(root.leftNode) + 1; // 如果没有右节点，遍历左节点，1为根节点
        return Math.max(maxDepthRecursive(root.leftNode), maxDepthRecursive(root.rightNode)) + 1; // 如果左右节点都存在，各自遍历左右节点取最大深度，1为根节点
    }

    /**
     * 方式2：队列实现
     *
     * T:O(n) S:O(n)
     */
    private int maxDepthIterative(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.leftNode != null) queue.add(node.leftNode);
                if (node.rightNode != null) queue.add(node.rightNode);
            }
            ++depth;
        }

        return depth;
    }
}
