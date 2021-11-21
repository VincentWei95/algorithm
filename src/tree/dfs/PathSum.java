package tree.dfs;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 113.路径总和2
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例1：
 *
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \      \
 *        7   2       1
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 */
public class PathSum {

    /**
     * 1、确定递归函数参数和返回值
     * 前序遍历直到叶子结点，需要遍历整棵树参数 TreeNode
     * 记录路径的列表参数 List<Integer> list
     * 记录所有路径的列表参数 List<List<Integer> result
     * 统计节点参数 targetSum
     * 需要搜索整棵树，不需要返回值 void
     *
     * 2、确定递归终止条件
     * 到达叶子节点时返回
     *
     * 3、确定单层递归逻辑
     * 前序遍历如果到达叶子节点时，targetSum == 0，将 list 添加到 result
     * 回溯时 list.remove(list.size() - 1)
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Integer> list = new ArrayList<>();
        traversal(root, targetSum, list, result);
        return result;
    }

    private void traversal(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> result) {
        targetSum -= root.val;
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        if (root.left != null) {
            traversal(root.left, targetSum, list, result);
            list.remove(list.size() - 1); // 回溯
        }
        if (root.right != null) {
            traversal(root.right, targetSum, list, result);
            list.remove(list.size() - 1); // 回溯
        }
    }
}
