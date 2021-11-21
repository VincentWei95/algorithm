package tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/**
 * 222.完全二叉树的节点个数
 *
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 示例 1：
 *              1
 *            /  \
 *           2    3
 *         /  \  /
 *        4   5 6
 *
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 *
 * 示例 2：
 *
 * 输入：root = []
 * 输出：0
 *
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：1
 */
public class CountNodes {

    /**
     * T:O(n)
     * S:O(n)
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int num = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            num += size;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) continue;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return num;
    }
}
