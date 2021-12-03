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
     * 上面的节点指向是需要根据题目推导的。例如，按题目第一次需要实现的目的是交换 2 和 1：
     *
     *                   ->
     * dummyHead  ->  1  <-  2  ->  3  ->  4
     *
     * 但交换这两个节点就涉及到节点 1 的前一个节点指针指向和节点 2 下一个节点指针指向，即影响了 dummyHead 和 节点 3
     * 所以在交换时需要考虑这两个节点。那怎么交换呢？
     *
     * 当节点 2 指向 节点1 时，需要做的还有两步：
     * 1、节点 1 指向 节点 3 形成 2 -> 1 -> 3 -> 4
     * 2、dummyHead 指向新的头节点即 节点2 形成 dummyHead -> 2 -> 1 -> 3 -> 4
     *
     * 剩下的就是什么时候交换哪个节点。
     *
     *  dummyHead ->  1  ->  2  ->  3  -> 4
     *     temp     node1  node2
     *
     *
     * 1、步骤1：dummyHead -> 节点2
     *
     * dummyHead  ->  2  ->  3  ->  4
     *
     * dummyHead      1  ->  2  ->  3  -> 4
     *
     * 2、步骤2：节点1 指向节点 3
     *
     * 1   ->   3  ->  4
     *
     * dummyHead     1      2   ->  3  ->  4
     *
     * 3、步骤3：节点2 指向 节点1
     *
     * dummyHead  ->  2  ->  1  ->  3  ->  4
     *   temp       node2  node1
     *
     * 所以有如下指针指向：
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
