package tree.dfs;

import tree.TreeNode;

/**
 * 654.最大二叉树
 *
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 *
 * 示例1：
 *
 *                6
 *              /  \
 *             3    5
 *             \   /
 *              2 0
 *               \
 *                1
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 *
 * 示例2：
 *
 *            3
 *             \
 *              2
 *               \
 *                1
 *
 * 输入：nums = [3,2,1]
 * 输出：[3,null,2,null,1]
 */
public class ConstructMaximumBinaryTree {

    /**
     * 题目解法与{@link BuildTree} 相同
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return traversal(nums, 0, nums.length);
    }

    private TreeNode traversal(int[] nums, int left, int right) {
        // 空节点
        if (right - left < 1) {
            return null;
        }

        // 叶子节点
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }

        int rootValue = nums[left];
        int rootIndex = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > rootValue) {
                rootValue = nums[i];
                rootIndex = i;
            }
        }

        TreeNode root = new TreeNode(rootValue);
        root.left = traversal(nums, left, rootIndex);
        root.right = traversal(nums, rootIndex + 1, right);
        return root;
    }
}
