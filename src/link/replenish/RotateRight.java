package link.replenish;

import link.ListNode;

/**
 * 61.旋转链表
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例1：
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 *          1->2->3->4->5
 * rotate1  5->1->2->3->4
 * rotate2  4->5->1->2->3
 *
 * 示例2：
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *          0->1->2
 * rotate1  2->0->1
 * rotate2  1->2->0
 * rotate3  0->1->2
 * rotate3  2->0->1
 */
public class RotateRight {

    /**
     * 1、获取到链表长度，然后计算 fast 要走的位置
     *   1   ->  2  ->  3  ->  4  ->  5
     * fast
     *
     * 2、fast 移动到 n = length % k 位置
     *   1   ->  2  ->  3  ->  4  ->  5
     *                fast
     *
     * 3、提供 slow，fast继续移动到链尾节点时，slow 也移动到新的链尾节点
     *   1   ->  2  ->  3  ->  4  ->  5
     *                slow          fast
     *
     * 4、原先的链尾节点的后继节点指向当前头节点 head，fast.next=head
     *   1   ->  2  ->  3  ->  4  ->  5  ->  1
     *                slow          fast
     *
     * 5、新的头节点为 slow.next，head = slow.next，slow.next 再设置为新的尾节点，slow.next = null
     *   4  ->  5  ->  1  ->  2  ->  3
     *  head  fast                 slow
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        int length = getLength(head);
        // 当 k > length 时，走 k % length 步和走 k 步是一样的
        int n = k % length;
        // n 是链表长度的倍数，直接返回原链表即可
        if (n == 0) return head;

        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        // 移动 fast 指针到链尾，此时 slow 会停在倒数第 k 个节点的位置
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 将链尾和头节点相连形成闭环
        fast.next = head;
        // slow 的后继节点为新的头节点
        head = slow.next;
        // 断开 slow 的后继节点，形成旋转后的链表
        slow.next = null;
        return head;
    }

    private int getLength(ListNode head) {
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }
}
