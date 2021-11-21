package tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/**
 * 111.二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例1：
 *
 *              3
 *            /  \
 *           9    20
 *               /  \
 *             15    7
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 */
public class MinDepth {

    /**
     * T:O(n)
     * S:O(n)
     */
    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null) return depth;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) continue;

                // 到达叶子节点的时候，说明到了最小深度
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return depth;
    }
}
