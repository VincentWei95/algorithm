package tree.middle;

import tree.TreeNode;

/**
 * 最大二叉树：
 *
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {

    }

    private TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    /**
     * 从限定的范围内找到数组的最大值创建根节点并获取最大值的索引maxIndex
     * 以maxIndex分割左右节点继续递归
     */
    private TreeNode helper(int[] nums, int left, int right) {
        if (left == right) return null;

        int max = 0;
        int maxIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = helper(nums, left, maxIndex);
        root.right = helper(nums, maxIndex + 1, right);
        return root;
    }
}
