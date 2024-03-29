package tree.bfs.replenish;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的逆层序遍历：
 *
 * 给你一棵二叉树，要求你从叶子节点到根节点一层一层地对其进行访问，对于每一层的节点，则是从左向右进行访问。将访问的结果以二维数组返回
 *
 * 这道题目和二叉树层序遍历的唯一区别是，它是从下向上一层一层去访问的
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     4
 *        / \
 *       8  16
 *
 * 它的逆层序遍历结果是：
 *
 * [
 *  [8, 16]，
 *  [2, 4],
 *  [1],
 * ]
 */
public class LevelOrderTraversalFromBottom {

    public static void main(String[] args) {

    }

    /**
     * 需要一个辅助队列：
     *
     * -----------------------
     * 1 |
     * -----------------------
     * 根节点入队，以入队数量为限定做循环，入队数量size=1
     *
     * -----------------------
     * 2 | 4 |
     * -----------------------
     * 根节点出队，出队节点有左右节点则入队，并用列表存储出队列表
     *
     * -----------------------
     * 8 | 16 |
     * -----------------------
     * 继续出队，2没有子节点直接出队存储到列表；4有子节点入队左右节点
     *
     * 剩余出队节点都没有左右节点，存储列表结果为：
     * [
     *  [1]，
     *  [2, 4],
     *  [8, 16],
     * ]
     * 以中心轴对称的元素两两对换就能获取到逆序结果：
     * [
     *  [8, 16]，
     *  [2, 4],
     *  [1],
     * ]
     *
     * T:O(n)
     * S:O(n)
     */
    private List<List<Integer>> levelOrderTraversalFromBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>(); // 存储每次出队节点列表
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> elements = new ArrayList<>(); // 存储出队节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                elements.add(node.val);
                // 如果出队节点有左右节点，则入队
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(elements);
        }

        // 存储出队节点头尾对调
        // i和j分别指向列表头尾的指针，向中间靠拢交换
        for (int i = 0; i < result.size() / 2; i++) {
            int j = result.size() - 1 - i;
            List<Integer> tmp = result.get(j);
            result.set(j, result.get(i));
            result.set(i, tmp);
        }
        return result;
    }

    /**
     * 扩展：103.二叉树的锯齿形层次遍历
     *
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 返回锯齿形层次遍历如下
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isReverse = false;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                // 如果没有反转左右节点，此次反转入队
                if (!isReverse) {
                    if (node.left != null) {
                        queue.add(node.right);
                    }
                    if (node.right != null) {
                        queue.add(node.left);
                    }
                }
                // 反转过左右节点，按正常入队
                else {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                // 修改标志未
                isReverse = !isReverse;
            }
            result.add(list);
        }
        return result;
    }
}
