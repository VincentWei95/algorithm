package link;

/**
 * 19.删除链表的倒数第N个节点：
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        RemoveNthFromEnd main = new RemoveNthFromEnd();
        main.removeNthFromEnd2(head, 2);
    }

    /**
     * 方式1：两边遍历实现
     *
     * T:O(L) 进行了两次遍历，首先计算了列表的长度 L 其次找到第 (L−n) 个结点。 操作执行了 2L−n 步
     * S:O(1)
     */
    private ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        int length = getLength(head);
        dummy.next = head;
        ListNode cur = dummy;
        // 移动到待删除节点的前一个位置
        for (int i = 0; i < length - n; ++i) {
            cur = cur.next;
        }
        // 删除节点
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLength(ListNode head) {
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 方式2：双指针实现
     *
     * 提供两个指针first和second，first指针先移动到待操作链表节点，然后first继续移动到链表结尾的同时second指针同时移动
     * first移动到链表结尾时，second移动到待操作节点，second指向下下个链表节点即删除了节点完成操作
     *
     * dummyHead  ->  1  ->  2  ->  3  ->  4  -> 5
     *  fast
     *  slow
     *
     * 1、fast 走 n 步到达待删除的节点
     *
     * dummyHead  ->  1  ->  2  ->  3  ->  4  -> 5
     *                            fast
     *  slow
     *
     * 2、走到 fast == null 时，slow 也停在待删除的前一个节点位置
     *
     * dummyHead  ->  1  ->  2  ->  3  ->  4  -> 5 -> null
     *                                                fast
     *                             slow
     *
     * T:O(L)
     * S:O(1)
     */
    private ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = dummyHead;
        // first移动到待操作链表节点
        for (int i = 1; i <= n; i++) {
            first = first.next;
        }
        // second移动到待操作链表节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 删除链表节点
        second.next = second.next.next;
        return dummyHead.next;
    }
}
