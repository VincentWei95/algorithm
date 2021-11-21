package link.replenish;

import link.ListNode;

/**
 * 2.两数相加：
 *
 * 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 参考：{@link string.AddStrings}
 */
public class AddTwoNumbers {

    /**
     * T:O(max(m,n))，假设 m 和 n 分别表示 l1 和 l2 的长度，上面的算法最多重复max(m,n) 次
     * S:O(max(m,n)), 新列表的长度最多为max(m,n) + 1
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int count = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + count;
            count = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (count > 0) {
            cur.next = new ListNode(count);
        }
        return dummyHead.next;
    }
}
