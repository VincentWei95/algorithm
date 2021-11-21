package tree.dfs;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 257.二叉树的所有路径
 *
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例1：
 *
 *          1
 *         / \
 *        2   3
 *         \
 *          5
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 *
 * 示例2：
 *
 * 输入：root = [1]
 * 输出：["1"]
 */
public class BinaryTreePaths {

    /**
     * 这道题目涉及到需要回溯，要记住：递归和回溯是一一对应的，有一个递归，就要有一个回溯，必须放在一起
     *
     * 1、确定递归函数参数和返回值
     * 要传入根节点需要一个参数 TreeNode，记录每个节点的数值 List<Integer>，记录最终的路径结果 List<String>；不需要返回值 void
     *
     * 2、确定递归终止条件
     * 因为需要回溯，这里的递归条件就不是判断 root == null，而是到达叶子节点 root.left == null && root.right == null
     *
     * 3、确定单层递归逻辑
     * 要记录每个节点使用前序遍历，当节点 root.left == null 或 root.right == null 时，就不需要再递归了
     * 有了递归，当然也需要有回溯，回溯时移除存储的最后一个节点
     *
     * T:O(n)
     * S:O(n)
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, result);
        return result;
    }

    private void traversal(TreeNode root, List<Integer> paths, List<String> result) {
        paths.add(root.val);
        // 到达叶子节点，将节点拼接为路径添加到路径列表
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));

            result.add(sb.toString());
            return;
        }
        if (root.left != null) {
            traversal(root.left, paths, result); // 递归
            paths.remove(paths.size() - 1); // 回溯，递归和回溯是一一对应的要放在一起
        }
        if (root.right != null) {
            traversal(root.right, paths, result); // 递归
            paths.remove(paths.size() - 1); // 回溯，递归和回溯是一一对应的要放在一起
        }
    }
}
