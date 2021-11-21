package tree.bfs;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/**
 * 513.找树左下角的值
 *
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 * 示例1：
 *
 *          2
 *         / \
 *        1  3
 *
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例2：
 *
 *          1
 *         / \
 *        2   3
 *       /   / \
 *      4   5  6
 *         /
 *        7
 *
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 */
public class FindBottomLeftValue {

    /**
     * 这道题目适合用层序遍历
     *
     * T:O(n)
     * S:O(n)
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;

        int num = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) continue;

                // 左节点是先进队列的，所以遍历到最后的第一个节点就是左下角的最左边的值
                if (i == 0) {
                    num = node.val;
                }
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
