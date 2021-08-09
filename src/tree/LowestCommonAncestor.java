package tree;

import java.util.ArrayList;
import java.util.List;

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
        LowestCommonAncestor main = new LowestCommonAncestor();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        System.out.println(main.lowestCommonAncestor2(root, new TreeNode(3), new TreeNode(5)).val);
    }

    /**
     * 方式：两次遍历
     *
     * 先各自遍历从根节点到达两个节点的路径
     * 有了两个从根节点到达目标节点（p和q）的路径，这两个节点的分岔节点，就是公共祖先
     *
     * T:O(n)
     * S:O(n)
     */
    private TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = getPath(root, p);
        List<TreeNode> pathQ = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < pathP.size() && i < pathQ.size(); i++) {
//            p = 6, q = 6, ancestor = 6
//            p = 2, q = 2, ancestor = 2
//            p = 4, q = 4, ancestor = 4
//            p = 3, q = 5, break
//            ancestor = 4
            if (pathP.get(i) == pathQ.get(i)) {
                ancestor = pathP.get(i);
            } else {
                // 不相同，上一个值就是公共祖先
                break;
            }
        }
        return ancestor;
    }

    /**
     *          6
     *        /   \
     *       2     8
     *      / \   / \
     *     0   4 7   9
     *        / \
     *       3   5
     * 假设要找的节点target是4，从根节点搜索
     * 如果 当前节点 > target，如 6 > 4，往左边走
     * 如果 当前节点 < target，如 6 < 9，往右边走
     */
    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else if (target.val > node.val){
                node = node.right;
            } else {
                break;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 方式2：递归实现
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
        int parentVal = root.val; // 当前节点
        int pVal = p.val; // 目标左节点
        int qVal = q.val; // 目标右节点

        // 因为是两个目标节点的公共节点，所以走过的路径在找到公共祖先节点之前应该是相同的
        // 所以可以使用以下的判断
        // 当前节点 > 两个目标节点，往左边走
        // 当前节点 < 两个目标节点，往右边走
        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        } else {
            return root;
        }
    }

    /**
     * 方式3：循环实现
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

    /**
     * 扩展：二叉树的最近公共祖先
     *
     * root是p、q的最近公共祖先，则只可能为以下情况之一：
     * 1、p和q在root的子树中，且分列为root的异侧（即分别在左、右子树中）
     * 2、p=root，且q在root的左或右子树中
     * 3、q=root，且p在root的左或右子树中
     *
     *                   3
     *                 /   \
     *                5     1
     *               / \   / \
     *              6   2 0   8
     *                 / \
     *                7   4
     * p=7，q=8，root=3
     * 3 是7、8的公共祖先，5不是8的祖先，1不是7的祖先 -> 3是7、8的最近公共祖先
     *
     * 考虑通过递归对二叉树进行前序遍历，当遇到节点p或q时返回。
     * 从底至顶回溯，当节点p、q在节点root的异侧时，节点root即为最近公共祖先，则向上返回root
     *
     * 递归解析：
     * 1、终止条件：
     * （1）当越过叶节点，则直接返回null
     * （2）当root等于p、q，则直接返回root
     * 2、递归步骤：
     * （1）递归左子节点，返回值记为left
     * （2）递归右子节点，返回值记为right
     * 3、返回值：根据left和right，可展开为四种情况：
     * （1）当left和right同时为空：说明root的左/右子树中都不包含p、q，返回null
     * （2）当left和right同时不为空：说明p、q分列在root的异侧（分别在左/右子树），因此root为最近公共祖先，返回root
     * （3）当left为空，right不为空：p、q都不在root的左子树中，直接返回right。具体可分为两种情况：
     *      [1]p、q其中一个在root的右子树中，此时right指向p
     *      [2]p、q两节点都在root的右子树中，此时的right指向最近公共祖先节点
     * （4）当left不为空，right为空：与情况（3）同理
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 树为null，直接返回null
        if (root == null) return null;
        // 如果p和q中有等于root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
        if (root == p || root == q) return root;

        // 遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果左子树中p和q都找不到，则p和q一定在右子树中，右子树中先遍历到的那个就是最近公共祖先
        if (left == null) return right;
        // 如果右子树中p和q都找不到，则p和q一定在左子树中，左子树中先遍历到的那个就是最近公共祖先
        if (right == null) return left;
        // left和right均不为空时，说明p和q节点分别在root的两侧，root为它们的最近公共祖先
        return root;
    }
}
