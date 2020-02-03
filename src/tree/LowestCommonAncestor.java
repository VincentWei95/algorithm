package tree;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先：
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *       6
 *     |  \
 *    2    8
 *   | \  | \
 *  0  4 7  9
 *    | \
 *    3 5
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身
 *
 * 说明：
 * 所有节点的值都是唯一的
 * p、q 为不同节点且均存在于给定的二叉搜索树中
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    /**
     * 方式1：递归实现
     *
     * 根据二分搜索树的特性：
     * 节点N左子树上的所有节点的值都小于等于节点N的值
     * 节点N右子树上的所有节点的值都大于等于节点N的值
     * 左子树和右子树也是二分搜索树
     *
     * 根据二分搜索树的特性可以分析：
     * 1、从根节点开始遍历树
     * 2、如果节点p和节点q都在右子树上，那么以右孩子为根节点继续操作
     * 3、如果节点p和节点q都在左子树上，那么以左孩子为根节点继续操作
     * 4、如果条件2和条件3都不成立，意味着已经找到公共节点
     *
     * T:O(n)
     * S:O(1)
     */
    private TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        } else {
            return root;
        }
    }

    /**
     * 方式2：循环实现
     *
     * T:O(n)
     * S:O(1)
     */
    private TreeNode lowestCommonAncestorIteractive(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;

        while (node != null) {
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }
}
