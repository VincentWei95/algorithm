package link.replenish;

import link.ListNode;
import tree.TreeNode;

/**
 * 109.有序链表转换为二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class SortedListToBST {

    /**
     * 分治实现
     *
     * T:O(nlogn)，其中 n 是链表的长度。
     *
     * 设长度为 n 的链表构造二叉搜索树的时间为 T(n)，递推式为 T(n) = 2 * T(n/2) + O(n)
     * 根据主定理，T(n) = O(nlogn)
     *
     * S:O(logn)，这里只计算除了返回答案之外的空间。
     * 平衡二叉树的高度为 O(logn)即为递归过程中栈的最大深度，也就是需要的空间
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        return helper(head, null);
    }

    private TreeNode helper(ListNode left, ListNode right) {
        if (left == right) return null;

        ListNode middle = getMiddle(left, right);
        TreeNode root = new TreeNode(middle.val);
        root.left = helper(left, middle);
        root.right = helper(middle.next, right);
        return root;
    }

    private ListNode getMiddle(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
