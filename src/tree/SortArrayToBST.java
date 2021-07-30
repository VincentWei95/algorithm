package tree;

import java.util.Random;

/**
 * 将有序数组转换为二叉搜索树
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * 示例1：
 *        0
 *      /   \
 *    -3     9
 *    /     /
 * -10     5
 *
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 *        0
 *      /   \
 *    -10    5
 *      \     \
 *      -3     9
 *
 * 示例1：
 *
 *      3       1
 *    /          \
 *  1             3
 *
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 按 严格递增 顺序排列
 */
public class SortArrayToBST {

    public static void main(String[] args) {

    }

    /**
     * 需要注意的条件：
     *
     * 1、数组的元素升序排列
     * 2、转换为一棵高度平衡的二叉搜索树
     *
     * 数组元素从小到大排列，二叉搜索树的中序遍历就是从小到大遍历的序列，但满足条件1并不可以唯一确定二叉搜索树
     * 要转换为高度平衡的二叉搜索树，满足该条件也不能唯一确定二叉搜索树
     *
     * 因为没有唯一解，所以该题目有多种解法
     *
     * 方式1：中序遍历，总选择中间位置左边的数字作为根节点
     *
     * T:O(n) n是数组长度
     * S:O(logn) n是数组长度，递归栈的深度
     */
    private TreeNode sortArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 方式2：中序遍历，总是选择中间位置右边的数字作为根节点
     *
     * T:O(n) n是数组长度
     * S:O(logn) n是数组长度，递归栈的深度
     */
    private TreeNode sortArrayToBST2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }

    /**
     * 方式3：中序遍历，选择任意一个中间位置数字作为根节点
     *
     * T:O(n) n是数组长度
     * S:O(logn) n是数组长度，递归栈的深度
     */
    private static final Random random = new Random();
    private TreeNode sortArrayToBST3(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper3(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择任意一个中间位置数字作为根节点
        int mid = (left + right + random.nextInt(2)) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }
}
