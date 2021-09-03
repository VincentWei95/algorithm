package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的第k大节点
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *   2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthLargest {

    public static void main(String[] args) {

    }

    /**
     * 自己的实现方式：
     *
     * 中序遍历获取到一个排好序的元素列表，再获取倒数第k个节点
     */
    public int kthLargest1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int index = list.size() - k;
        if (index < 0) return 0;
        return list.get(index);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    /**
     * 已知中序遍历可以获取到一个排好序的元素列表，该题需要获取倒序的第k个节点，那可以反过来中序遍历
     *
     * 升序的中序遍历：
     * dfs(root.left); // 左
     * print(root.val); // 根
     * dfs(root.right); // 右
     *
     * 结果：1 2 3 4 5 6
     *
     * 降序的中序遍历：
     * dfs(root.right); // 右
     * print(root.val); // 根
     * dfs(root.left); // 左
     *
     * 结果：6 5 4 3 2 1
     *
     * 求第k个节点，需要实现以下三项工作：
     * 1、递归遍历时计数，统计当前节点的序号
     * 2、递归到第k个节点时，应记录结果res
     * 3、记录结果后，后续的遍历即失去意义，应提前终止返回
     *
     * 递归解析：
     * 1、终止条件：当节点root为空（越过叶子节点），则直接返回
     * 2、递归右子树：dfs(root.right)
     * 3、三项工作：
     *  （1）提前返回：若k=0，代表已找到目标节点，无需继续遍历，直接返回
     *  （2）统计序号：执行k=k-1
     *  （3）记录结果：若K=0，代表当前节点为第k大的节点，记录res=root.val
     * 4、递归左子树：dfs(root.left)
     */
    private int k;
    private int res;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs2(root);
        return res;
    }

    private void dfs2(TreeNode root) {
        if (root == null) return;
        dfs2(root.right);
        if (k == 0) return;
        if (--k == 0) {
            res = root.val;
            return;
        }
        dfs2(root.left);
    }
}
