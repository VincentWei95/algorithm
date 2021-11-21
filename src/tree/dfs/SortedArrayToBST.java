package tree.dfs;

import tree.TreeNode;

/**
 * 108.将有序数组转换为二叉搜索树
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * 示例1：
 *
 *          0
 *         / \
 *       -3   9
 *       /   /
 *     -10  5
 *
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 *      0
 *     / \
 *   -10  5
 *     \   \
 *     -3   9
 *
 * 示例2：
 *
 *          3  1
 *         /    \
 *        1      3
 *
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 */
public class SortedArrayToBST {

    /**
     * 1、确定递归函数参数和返回值
     * 构造二叉搜索树时需要分割数组，参数需要数组以及限定的分割范围
     * 返回值 TreeNode
     *
     * 2、确定递归终止条件
     * left > right, return null
     *
     * 3、确定单层递归逻辑
     * 因为数组是升序的，所以可以取数组的中间节点作为根节点，然后分割为左数组和右数组递归作为左右子树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length - 1);
    }

    private TreeNode traversal(int[] nums, int left, int right) {
        if (left > right) return null;

        // 遵循左闭右闭[left, right]
        int rootIndex = left + (right - left) / 2; // 注意二分法会出现的溢出
        TreeNode root = new TreeNode(nums[rootIndex]);
        root.left = traversal(nums, left, rootIndex - 1);
        root.right = traversal(nums, rootIndex + 1, right);
        return root;
    }
}
