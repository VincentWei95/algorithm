package link.middle;

import link.ListNode;

/**
 * 分隔链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 1->4->3->2->5->2 => 1->2->2->4->3->5
 */
public class Partition {

    /**
     * 实现思路：
     *
     * 提供两个链表，一个构建小于 x 的节点的链表 small，一个构建大于等于 x 的节点的链表 large
     * 最终将 small 链表的尾指针指向 large 链表的头节点
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null; // large 是尾指针
        small.next = largeHead.next; // small 链表的尾节点指向 large 链表的头节点
        return smallHead.next;
    }
}
