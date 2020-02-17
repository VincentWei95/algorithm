package tree.middle;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 找树左下角的值：
 *
 * 给定一个二叉树，在树的最后一行找到最左边的值
 *
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 */
public class FindBottomLeftValue {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归 dfs
     *
     * T:O(n)
     * S:O(n)
     */
    private int maxDepth;
    private int result;
    private int findBottomLeftValueRecursive(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;

        dfs(root.left, depth + 1);
        if (depth > maxDepth) {
            maxDepth = depth;
            result = root.val;
        }
        dfs(root.right, depth + 1);
    }

    /**
     * 方式2：迭代 bfs
     *
     * T:O(n)
     * S:O(n)
     */
    private int findBottomLeftValueIteractive(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 获取的是每一层左节点的值
            result = queue.peek().val;

            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                // 先入队左节点
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
}
