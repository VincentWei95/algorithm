package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径：
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归
     *
     * T:O(n)
     * S:O(n)
     */
    private List<String> binaryTreePathsRecursive(TreeNode root) {
        List<String> result = new ArrayList<>();
        constructPath(root, "", result);
        return result;
    }

    private void constructPath(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            path += String.valueOf(root.val);

            // 已经到叶子节点，将路径添加到列表中
            if (root.left == null && root.right == null) {
                paths.add(path);
            } else {
                path += "->";
                constructPath(root.left, path, paths);
                constructPath(root.right, path, paths);
            }
        }
    }
}
