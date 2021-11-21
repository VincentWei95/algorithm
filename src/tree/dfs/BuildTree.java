package tree.dfs;

import tree.TreeNode;

/**
 * 106.从中序与后序遍历序列构造二叉树
 * 105.从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTree {

    /**
     * 从中序与后序遍历序列构造二叉树
     *
     * 思路：
     * 1、如果数组大小为0，说明是空节点
     * 2、如果不为空，那么取后序数组最后一个元素作为节点元素（根节点）
     * 3、找到后序数组最后一个元素在中序数组的位置，作为切割点
     * 4、切割中序数组，切成中序左数组和中序右数组（先切割中序数组，再切割后序数组）
     * 5、切割后序数组，切成后序左数组和后序右数组
     * 6、递归处理左区间和右区间
     *
     * 注意事项：
     * 1、数组切割遵循循环不变量原则，左闭右开
     * 2、后序数组的切割要点：后序数组和中序数组大小相同
     *
     * T:O(n)
     * S:O(n)
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode traversal(int[] inorder, int inLeft, int inRight,
                               int[] postorder, int postLeft, int postRight) {
        // 空节点
        if (inRight - inLeft < 1) {
            return null;
        }

        // 叶子节点
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }

        // 从后序数组中拿到二叉树根节点
        int rootVal = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootVal);

        // 找到中序数组的切割点位置
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        // 切割中序数组左区间和右区间，左闭右开
        int inorderLeftBeginIndex = inLeft;
        int inorderLeftEndIndex = rootIndex;
        int inorderRightBeginIndex = rootIndex + 1;
        int inorderRightEndIndex = inRight;

        // 切割后序数组左区间和右区间，中序数组和后序数组大小相同
        // rootIndex - inLeft 是中序左数组的长度[0, rootIndex)
        int postorderLeftBeginIndex = postLeft;
        int postorderLeftEndIndex = postLeft + (rootIndex - inLeft);
        // rootIndex - inLeft 是中序右数组的长度[rootIndex + 1, end)
        int postorderRightBeginIndex = postLeft + (rootIndex - inLeft);
        int postorderRightEndIndex = postRight - 1; // 最后一个元素已经作为二叉树根节点要去除

        root.left = traversal(inorder, inorderLeftBeginIndex, inorderLeftEndIndex,
                postorder, postorderLeftBeginIndex, postorderLeftEndIndex);
        root.right = traversal(inorder, inorderRightBeginIndex, inorderRightEndIndex,
                postorder, postorderRightBeginIndex, postorderRightEndIndex);

        return root;
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     *
     * 思路和上面相同
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return traversal2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode traversal2(int[] preorder, int preLeft, int preRight,
                               int[] inorder, int inLeft, int inRight) {
        if (inRight - inLeft < 1) {
            return null;
        }

        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }

        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int inorderLeftBeginIndex = inLeft;
        int inorderLeftEndIndex = rootIndex;
        int inorderRightBeginIndex = rootIndex + 1;
        int inorderRightEndIndex = inRight;

        int preorderLeftBeginIndex = preLeft + 1;
        int preorderLeftEndIndex = preLeft + 1 + (rootIndex - inLeft);
        int preorderRightBeginIndex = preLeft + 1 + (rootIndex - inLeft);
        int preorderRightEndIndex = preRight;

        root.left = traversal2(preorder, preorderLeftBeginIndex, preorderLeftEndIndex,
                inorder, inorderLeftBeginIndex, inorderLeftEndIndex);
        root.right = traversal2(preorder, preorderRightBeginIndex,preorderRightEndIndex,
                inorder, inorderRightBeginIndex, inorderRightEndIndex);

        return root;
    }
}
