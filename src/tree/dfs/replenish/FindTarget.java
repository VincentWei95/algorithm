package tree.dfs.replenish;

import java.util.HashSet;
import java.util.Set;

import tree.TreeNode;

/**
 * 167.两数之和 IV：输入BST
 *
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true
 *
 * 示例1：
 *
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 *
 * 示例2：
 *
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 *
 * 示例3：
 *
 * 输入: root = [2,1,3], k = 4
 * 输出: true
 *
 * 示例4：
 *
 * 输入: root = [2,1,3], k = 1
 * 输出: false
 *
 * 示例5：
 *
 * 输入: root = [2,1,3], k = 3
 * 输出: true
 */
public class FindTarget {

    public static void main(String[] args) {

    }

    /**
     * 方式1：dfs+HashSet
     *
     * HashSet存储节点数值，如果存在k-root.val，说明找到节点返回true，否则dfs遍历
     *
     * T:O(n)
     * S:O(n)
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    private boolean dfs(TreeNode root, Set<Integer> set, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
