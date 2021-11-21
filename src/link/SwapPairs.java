package link;

/**
 * 24.两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 1->2->3->4
 *
 * 2->1->4->3
 */
public class SwapPairs {

    /**
     * 创建哑节点 dummy，dummyHead.next = head，temp = dummyHead，每次需要交换 temp 后面的两个节点
     *
     * 如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要更换，因此结束交换。
     * 否则 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点
     *
     *  dummyHead ->  1  ->  2  ->  3  -> 4         2  ->  1  ->  3  -> 4
     *     temp     node1  node2                =>       temp   node1 node2
     *
     * 交换之前的节点关系是：temp -> node1 -> node2
     * 交换之后的节点关系是：temp -> node2 -> node1
     *
     * temp.next = node2;
     * node1.next = node2.next;
     * node2.next = node1;
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            temp = node1;
        }
        return dummyHead.next;
    }
}
