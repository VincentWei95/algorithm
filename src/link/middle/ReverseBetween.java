package link.middle;

import link.ListNode;

/**
 * 反转链表2
 *
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例1：
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 1->2->3->4->5 => 1->4->3->2->5
 *
 * 示例2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 */
public class ReverseBetween {

    /**
     * 实现思路：最主要是反转 left 到 right 之间的链表节点，然后再重新拼接链表
     *
     * 1、先走到 left - 1 节点，假设定义为 prev
     *
     *   1  ->  2  ->  3  ->  4  ->  5
     * prev
     *
     * --------------------------------------------------------------------------------------------
     *
     * 2、从 prev 再走 right - left + 1 节点，到达 right 节点，假设定义为 rightNode
     *
     *   1  ->  2  ->  3  ->  4  ->  5
     * prev                rightNode
     *
     * --------------------------------------------------------------------------------------------
     *
     * 3、截取节点 prev.next 和 rightNode.next 节点，假设定义为 leftNode 和 cur
     *
     *   1  ->  2  ->  3  ->  4   ->   5
     * prev  leftNode      rightNode  cur
     *
     * prev.next = leftNode = 2
     * rightNode.next = cur = 5
     *
     * --------------------------------------------------------------------------------------------
     *
     * 4、切断链接 prev.next = null 和 rightNode.next = null
     *
     *   1      2  ->  3  ->  4       5
     * prev  leftNode     rightNode  cur
     *
     * prev.next = leftNode = 2
     * rightNode.next = cur = 5
     *
     * --------------------------------------------------------------------------------------------
     *
     * 5、反转 left 到 right 位置的链表
     *
     *   1      2  <-  3  <-  4       5
     * prev  leftNode     rightNode  cur
     *
     * prev.next = leftNode = 2
     * rightNode.next = cur = 5
     *
     * --------------------------------------------------------------------------------------------
     *
     * 6、重新拼接 prev.next 和 rightNode.next 链表
     *
     *   1      2  <-  3  <-  4       5
     * prev  leftNode     rightNode  cur
     *
     * prev.next = rightNode
     * leftNode.next = cur
     *
     *   1   ->   4   ->  3  ->   2   ->  5
     * prev  rightNode        leftNode   cur
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 先走到 left - 1 节点，假设定义为 prev
        ListNode prev = dummyHead;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 从 prev 再走 right - left + 1 节点，到达 right 节点，假设定义为 rightNode
        ListNode rightNode = prev;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 截取节点 prev.next 和 rightNode.next 节点，假设定义为 leftNode 和 cur
        ListNode leftNode = prev.next;
        ListNode cur = rightNode.next;

        // 切断链接 prev.next = null 和 rightNode.next = null
        prev.next = null;
        rightNode.next = null;

        // 反转 left 到 right 位置的链表
        reverseListNode(leftNode);

        // 重新拼接 prev.next 和 rightNode.next 链表
        prev.next = rightNode;
        leftNode.next = cur;
        return dummyHead.next;
    }

    private void reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
}
