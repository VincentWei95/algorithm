package tree.dfs;

import tree.TreeNode;

/**
 * 538.把二叉搜索树转换为累加树
 *
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node的新值等于原树中大于或等于node.val的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *
 * 示例1：
 *
 *               4                    30
 *             /   \                /   \
 *            1     6             36     21
 *           / \   / \      ->   /  \   /  \
 *          0   2 5   7         36  35 26  15
 *               \     \             \       \
 *                3     8            33      8
 */
public class ConvertBST {

    /**
     * 一看到累加树，会存在的疑惑是：如何累加的？要理解二叉搜索树如何累加，可以转换一下思路：有序的数组元素如何累加？
     * 二叉搜索树是一个有序升序的数组，假设有一个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]
     * 我们知道二叉搜索树的中序遍历就是从前到后，数组元素数值从小到大升序；其实这道题目就是要反过来中序遍历，然后累加节点数值
     *
     * 1、确定递归函数参数和返回值
     * 参数是根节点，遍历整棵树不需要返回值
     *
     * 2、确定递归终止条件
     * root == null, return
     *
     * 3、确定单层递归逻辑
     * 反中序遍历，因为需要做累加，所以需要提供一个变量 sum 记录上一个累加后的数值
     */
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;

        traversal(root.right);
        root.val += sum;
        sum = root.val;
        traversal(root.left);
    }
}
