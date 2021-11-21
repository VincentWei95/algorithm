package tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

/**
 * 515.在每个树行中找最大值
 *
 * 给定一棵二叉树的根节点root ，请找出该二叉树中每一层的最大值。
 *
 * 示例1：
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 *           1
 *          / \
 *         2   3
 * 示例3：
 *
 * 输入: root = [1]
 * 输出: [1]
 *
 * 示例4：
 *
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 *           1
 *            \
 *             2
 *
 * 示例5：
 *
 * 输入: root = []
 * 输出: []
 */
public class LargestValues {

    /**
     * T:O(n)
     * S:O(n)
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }
}
