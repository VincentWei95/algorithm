package easy;

import java.util.Stack;

/**
 * 判断二叉树是否平衡：
 *
 * 给你一个二叉树，你要判断它是否沿中轴线对称
 *
 * 比如说，给你的二叉树是：
 *
 *      1
 *    /   \
 *   2     2
 *  / \   / \
 * 4   8 8   4
 *
 * 这棵二叉树是沿中轴线对称的，因此要返回 true。如果我去掉最后这个 4：
 *
 *      1
 *    /   \
 *   2     2
 *  / \   /
 * 4   8 8
 *
 * 就不对称了，这时就要返回 false。
 */
public class BinaryTreeSymmetry {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.leftNode = new TreeNode(2);
        root.rightNode = new TreeNode(2);
        root.leftNode.leftNode = new TreeNode(4);
        root.leftNode.rightNode = new TreeNode(8);
        root.rightNode.leftNode = new TreeNode(8);
        root.rightNode.rightNode = new TreeNode(4);

        BinaryTreeSymmetry main = new BinaryTreeSymmetry();
        System.out.println(main.isTreesymmetryStack(root));
    }

    /**
     * 方式1：递归法遍历树节点
     *
     * 根节点不需要对比，左节点对比右节点，左节点的左节点对比右节点的右节点，左节点的右节点对比右节点的左节点
     *
     * 依此类推，如果有其中一个节点为null另一个节点不为null肯定不是对称，否则是
     *
     * T:O(n) 需要遍历整个二叉树
     * S:O(n)
     */
    private boolean isTreeSymmetryRecursive(TreeNode root) {
        if (root == null) return true;
        return isTreeSymmetry(root.leftNode, root.rightNode);
    }

    private boolean isTreeSymmetry(TreeNode s, TreeNode t) {
        if (s != null && t != null) {
            return s.val == t.val
                    && isTreeSymmetry(s.leftNode, t.rightNode) // 左节点的左节点不断与右节点的右节点对比
                    && isTreeSymmetry(s.rightNode, t.leftNode); // 左节点的右节点不断与右节点的左节点对比
        }
        return s == null && t == null; // 到最后都为null说明是对称的
    }

    /**
     * 方式2：用栈的出入栈遍历判断树节点
     *
     * 根结点不需要对比，左节点和右节点入栈，如果栈不为空，弹栈对树节点进行对比
     *
     * T:O(n) 需要遍历整个二叉树
     * S:O(n) 需要一个栈
     */
    private boolean isTreesymmetryStack(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.leftNode);
        stack.push(root.rightNode);
        while (!stack.isEmpty()) {
            TreeNode s = stack.pop();
            TreeNode t = stack.pop();
            if (s == null && t == null) continue; // 如果两边节点都为null继续对比后续的左右节点
            if (s == null || t == null) return false; // 如果有一边节点为null肯定不对称
            if (s.val != t.val) return false; // 两边节点的值不相同也不是对称

            // 左节点的左节点和右节点的右节点依次入栈
            // 左节点的右节点和右节点的左节点依次入栈
            stack.push(s.leftNode);
            stack.push(t.rightNode);
            stack.push(s.rightNode);
            stack.push(t.leftNode);
        }
        return true;
    }

    private static class TreeNode {
        int val;
        TreeNode leftNode;
        TreeNode rightNode;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
