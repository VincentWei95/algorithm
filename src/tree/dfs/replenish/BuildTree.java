package tree.dfs.replenish;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105.从前序遍历与中序遍历序列构造二叉树：
 *
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 *
 * 示例1：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 提示：
 *
 * preorder和inorder均无重复元素
 * inorder均出现在preorder
 * preorder保证为二叉树的前序遍历序列
 * inorder保证为二叉树的中序遍历序列
 */
public class BuildTree {

    public static void main(String[] args) {

    }

    /**
     * preorder 中的第一个元素一定是树的根，这个根又将 inorder 序列分成了左右两棵子树。
     * 现在我们只需要将先序遍历的数组中删除根元素，然后重复上面的过程处理左右两棵子树
     */
    private int[] preorder;
    private int preIndex;
    private final Map<Integer, Integer> map = new HashMap<>();
    private TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recursion(0, inorder.length);
    }

    private TreeNode recursion(int left, int right) {
        if (left == right) return null;

        // 拿到preorder的根节点，创建根节点
        TreeNode root = new TreeNode(preorder[preIndex]);

        // 根据preorder根节点的通过map获取在inorder的索引
        int rootIndexInorder = map.get(preorder[preIndex]);

        // preorder的索引往后移动继续递归
        preIndex++;

        // 左节点为left到index，右节点为index+1到right
        root.left = recursion(left, rootIndexInorder);
        root.right = recursion(rootIndexInorder + 1, right);
        return root;
    }
}
