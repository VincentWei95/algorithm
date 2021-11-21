package tree.dfs;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/**
 * 144.二叉树的前序遍历
 * 94.二叉树的中序遍历
 * 145.二叉树的后序遍历
 */
public class TreeOrder {

    public List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursivePreOrder(root, list);
        return list;
    }

    private void recursivePreOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        list.add(root.val);
        recursivePreOrder(root.left, list);
        recursivePreOrder(root.right, list);
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursiveInOrder(root, list);
        return list;
    }

    private void recursiveInOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        recursivePreOrder(root.left, list);
        list.add(root.val);
        recursivePreOrder(root.right, list);
    }

    public List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursivePostOrder(root, list);
        return list;
    }

    private void recursivePostOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        recursivePreOrder(root.left, list);
        recursivePreOrder(root.right, list);
        list.add(root.val);
    }
}
