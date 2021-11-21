package tree.dfs.replenish;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 129.求根到叶子节点的数字之和：
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 *
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 *
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class SumNumbers {

    public static void main(String[] args) {

    }

    private int result;
    private int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) return;

        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            result += sum;
        } else {
            if (root.left != null) dfs(root.left, sum);
            if (root.right != null) dfs(root.right, sum);
        }
    }

    // 个人实现
    private int sumNumbersCustom(TreeNode root) {
        List<String> numStrs = new ArrayList<>();
        walkTree(root, "", numStrs);
        int sum = 0;
        for (String num : numStrs) {
            sum += Integer.valueOf(num);
        }
        return sum;
    }

    private void walkTree(TreeNode root, String numStr, List<String> numStrs) {
        if (root != null) {
            numStr += String.valueOf(root.val);
            if (root.left == null && root.right == null) {
                numStrs.add(numStr);
            } else {
                walkTree(root.left, numStr, numStrs);
                walkTree(root.right, numStr, numStrs);
            }
        }
    }
}
