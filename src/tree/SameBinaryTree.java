package tree;

import java.util.Stack;

/**
 * 判断二叉树是否相同：
 *
 * 给你两个二叉树，你要判断它们是否相同。这里所谓相同，指的是两棵树结构相同，并且相应节点上的数值相等。
 *
 * 比如说，给你的两棵二叉树都是：
 *
 *    1          1
 *   / \        / \
 *  2   4      2   4
 *
 * 它们的结构相同，相应节点上的值也相等，因此返回 true。 如果另一棵树是：
 *
 *    1
 *   / \
 *  2   5
 * 或
 *     1
 *    /
 *   2
 *  /
 * 4
 *
 * 两棵树则不相同，返回 false。
 *
 *
 */
public class SameBinaryTree {

    public static void main(String[] args) {
        SameBinaryTree main = new SameBinaryTree();
        TreeNode p = main.getFirstTree();
        TreeNode q = main.getSecondTree();
        System.out.println(main.isSameBinaryTreeRecursive(p, q));
    }

    private TreeNode getFirstTree() {
        TreeNode root = new TreeNode(1);
        root.leftNode = new TreeNode(2);
        root.rightNode = new TreeNode(2);
        root.leftNode.leftNode = new TreeNode(4);
        root.leftNode.rightNode = new TreeNode(8);
        root.rightNode.leftNode = new TreeNode(8);
        root.rightNode.rightNode = new TreeNode(4);
        return root;
    }

    private TreeNode getSecondTree() {
        TreeNode root = new TreeNode(1);
        root.leftNode = new TreeNode(2);
        root.rightNode = new TreeNode(2);
        root.leftNode.leftNode = new TreeNode(4);
        root.leftNode.rightNode = new TreeNode(8);
        root.rightNode.leftNode = new TreeNode(8);
        return root;
    }

    /**
     * 方式1：递归法遍历树节点
     *
     * 两棵树遍历对比左节点，然后遍历右节点
     *
     * T:O(n) 需要遍历整个二叉树
     * S:O(n)
     */
    private boolean isSameBinaryTreeRecursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val
                && isSameBinaryTreeRecursive(p.leftNode, q.leftNode)
                && isSameBinaryTreeRecursive(p.rightNode, p.rightNode);
    }

    /**
     * 方式2：用栈的出入栈遍历判断树节点
     *
     * 两棵树左节点和左节点入栈，如果栈不为空，弹栈对树节点进行对比
     *
     * 第一次入栈：
     *
     * 2 第二颗树的根节点
     * 2 第一棵树的根节点
     *
     * 第二次入栈：
     *
     * 8 第二颗树的右节点
     * 8 第一棵树的右节点
     * 4 第二颗树的左节点
     * 4 第一棵树的左节点
     *
     * T:O(n) 需要遍历整个二叉树
     * S:O(n) 需要一个栈
     */
    private boolean isSameBinaryTreeIterative(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);

        while (!stack.isEmpty()) {
            TreeNode s = stack.pop();
            TreeNode t = stack.pop();
            if (s == null && t == null) continue; // 如果两边节点都为null继续对比后续的左右节点
            if (s == null || t == null) return false; // 如果有一边节点为null肯定不相同
            if (s.val != t.val) return false; // 两边节点的值不相同也不相同

            stack.push(s.leftNode);
            stack.push(t.leftNode);
            stack.push(s.rightNode);
            stack.push(t.rightNode);
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
