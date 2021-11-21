package link.replenish;

import link.ListNode;

/**
 * 82.删除排序链表中的重复元素 2
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
 * 只保留原始链表中 没有重复出现 的数字。返回同样按升序排列的结果链表。
 *
 * 示例1：1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * 结果：1 -> 2 -> 3
 *
 * 示例2：1 -> 1 -> 1 -> 2 -> 3
 * 结果：2 -> 3
 */
public class DeleteDuplicates2 {

    public static void main(String[] args) {

    }

    /**
     * 思路：因为链表是升序排序的，如果存在重复的数字则是连续出现
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null && p.next.next != null) {
            // 找到相同的数字
            if (p.next.val == p.next.next.val) {
                int val = p.next.val;
                // 删除相同数字的节点
                while (p.next != null && p.next.val == val) {
                    p.next = p.next.next;
                }
            } else {
                p = p.next;
            }
        }
        return dummyHead.next;
    }
}
