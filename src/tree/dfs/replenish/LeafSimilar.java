package tree.dfs.replenish;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 872.叶子相似的树
 *
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列
 *
 *              3
 *            /  \
 *          5     1
 *         / \   / \
 *        6   2 9  8
 *           / \
 *          7  4
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。
 *
 * 如果给定的两个根结点分别为root1 和root2的树是叶相似的，则返回true；否则返回 false 。
 *
 * 示例1：
 *              3                   3
 *            /  \                /   \
 *          5     1              5     1
 *         / \   / \            / \   / \
 *        6   2 9  8           6   7 4   2
 *           / \                        / \
 *          7  4                       9   8
 *
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 *
 * 示例2：
 *
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 *
 * 示例3：
 *
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 *
 * 示例4：
 *
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 *
 * 示例5：
 *
 *          1        1
 *         / \      / \
 *        2  3     3   2
 *
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 */
public class LeafSimilar {

    public static void main(String[] args) {

    }

    /**
     * 实现思路：
     * 各自遍历二叉树的左右子树，递归结束到叶子节点时，用列表存储叶子节点
     * 两个二叉树遍历获取到两个叶子节点列表，比对是否相同
     *
     * T:O(n1+n2)
     * S:O(n)
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        List<TreeNode> root1Leafs = new ArrayList<>();
        List<TreeNode> root2Leafs = new ArrayList<>();
        getLeafs(root1, root1Leafs);
        getLeafs(root2, root2Leafs);
        if (root1Leafs.size() != root2Leafs.size()) return false;
        for (int i = 0; i < root1Leafs.size(); i++) {
            if (root1Leafs.get(i).val != root2Leafs.get(i).val) return false;
        }
        return true;
    }

    private void getLeafs(TreeNode root, List<TreeNode> leafs) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leafs.add(root);
            return;
        }
        getLeafs(root.left, leafs);
        getLeafs(root.right, leafs);
    }
}
