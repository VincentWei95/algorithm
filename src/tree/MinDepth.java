package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度：
 *
 * 给你一棵二叉树，你要找到从根节点到最近的叶子节点的深度
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 这棵树有 3 个叶子节点，分别是 2，8，16。最近的叶子节点是 2，根节点到 2 共有两个节点，因此最小深度是 2
 *
 * 再比如说，给你的二叉树是：
 *
 *   1
 *    \
 *     2
 *      \
 *       4
 *
 * 这棵树唯一的叶子节点是 4，根节点到它共有 3 个节点，因此最小深度是 3
 */
public class MinDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(16);

        MinDepth main = new MinDepth();
        System.out.println(main.minDepthIterative(root));
    }

    /**
     * 方式1：递归实现
     *
     * T:O(n) S:O(n)
     */
    private int minDepthRecursive(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1; // 只有根节点
        if (root.left == null) return minDepthRecursive(root.right) + 1; // 如果没有左节点，遍历右节点，1为根节点
        if (root.right == null) return minDepthRecursive(root.left) + 1; // 如果没有右节点，遍历左节点，1为根节点
        return Math.min(minDepthRecursive(root.left), minDepthRecursive(root.right)) + 1; // 如果左右节点都存在，各自遍历左右节点取最小深度，1为根节点
    }

    /**
     * 方式2：队列实现
     *
     * T:O(n) S:O(n)
     */
    private int minDepthIterative(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ++depth;
        }

        return -1;
    }
}
