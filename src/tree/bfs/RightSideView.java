package tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

/**
 * 199.二叉树的右视图
 *
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *      1     <-
 *     / \
 *    2   3   <-
 *     \   \
 *      5   4 <-
 *
 * 输入:[1,2,3,null,5,null,4]
 * 输出:[1,3,4]
 *
 * 示例 2:
 *
 * 输入:[1,null,3]
 * 输出:[1,3]
 *
 * 示例 3:
 *
 * 输入:[]
 * 输出:[]
 */
public class RightSideView {

    /**
     * T:O(n)
     * S:O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 右视图就是每一层最后的一个元素
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }
}
