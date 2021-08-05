package link;

/**
 * 链表中倒数第k个节点：
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5
 */
public class GetKthFromEnd {

    public static void main(String[] args) {

    }

    /**
     * 方式1：两边遍历
     *
     * 首次遍历获取链表长度，二次遍历到length-k位置
     *
     * T:O(L+(L-k))
     * S:O(1)
     */
    private ListNode getKthFromEnd1(ListNode head, int k) {
        int length = 0;
        for (ListNode p = head; p != null; p = p.next) {
            length++;
        }

        ListNode cur = head;
        for (int i = 0; i < length - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 方式2：一次遍历
     *
     * k = 2
     *
     * 步骤1：
     * i = 1
     *      p
     * q
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * 步骤2：
     * i = 2
     *           p
     * q
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * 步骤3：
     * i = 3
     *                p
     *      q
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * 步骤4：
     * i = 4
     *                     p
     *           q
     * 1 -> 2 -> 3 -> 4 -> 5
     *
     * 步骤5：
     * i = 5
     *                          p
     *                q
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     *
     * T:O(L)
     * S:O(1)
     */
    private ListNode getKthFromEnd2(ListNode head, int k) {
        int i = 0;
        ListNode p = head;
        ListNode q = head;
        while (p != null) {
            if (i >= k) {
                q = q.next;
            }
            p = p.next;
            i++;
        }
        return q;
    }
}
