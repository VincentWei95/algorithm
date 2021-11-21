package link;

/**
 * 203.移除链表元素
 *
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 示例1：
 *
 * 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
 *
 * 结果：1 -> 2 -> 3 -> 4 -> 5
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 *
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
public class RemoveElements {

    /**
     * int val = 6
     * dummyHead   1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
     *    cur  cur.next
     *
     * 判断 cur.next.val == val，cur.next 指向下下个节点即 cur.next = cur.next.next
     * 否则 cur 往前走
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
