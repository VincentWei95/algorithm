package tree.middle;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历：
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 */
public class InorderTraversal {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归实现
     *
     * T:O(n)
     * S:O(n)，平均情况O(logn)
     */
    private List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        // list.add(root.val) 前序遍历

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);

        // list.add(root.val) 后序遍历
    }

    /**
     * 方式2：栈实现
     *
     * T:O(n)
     * S:O(n)
     */
    private List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }
}
