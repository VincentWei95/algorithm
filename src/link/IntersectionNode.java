package link;

/**
 * 面试题02、07.链表的相交节点：
 *
 * 给你两个单链表，你要找到它们相交的第一个节点。如果两个链表没有相交，则返回空指针。
 * 假设链表无环，并且你不能改变它的原始结构。另外要求算法是线性时间复杂度，空间复杂度要求是 O(1)
 *
 * 比如说，两条链表分别是：
 *
 * A:     1 -> 2
 *               \
 *                6 -> 7 -> null
 *               /
 * B: 3 -> 4 -> 5
 *
 * 你要返回的是 6 这个节点
 */
public class IntersectionNode {

    public static void main(String[] args) {
        ListNode link1 = new ListNode(1);
        link1.next = new ListNode(2);
        link1.next.next = new ListNode(6);
        link1.next.next.next = new ListNode(7);

        ListNode link2 = new ListNode(3);
        link2.next = new ListNode(4);
        link2.next.next = new ListNode(5);
        link2.next.next.next = link1.next.next;
        link2.next.next.next.next = link1.next.next.next;

        IntersectionNode main = new IntersectionNode();
        System.out.println(main.getIntersectionNodeWithoutLen(link1, link2));
    }

    /**
     * 方式1：双指针实现（需要计算两个链表长度）
     *
     * 要先求出两个链表的长度，提供两个指针p和q分别指向两个链表，先根据长度求出链表长度差，
     * 长的链表指针q先走长度差步移动到和短的链表p指向的长度位置，然后开始两个指针同时移动对比找到相交节点
     *
     * A:     1 -> 2
     *        p       \
     *                6 -> 7 -> null
     *               /
     * B: 3 -> 4 -> 5
     *    q -> q 移动到和指针p相同长度位置
     *
     * T:O(m+n)
     * S:O(1)
     */
    private ListNode getIntersectionNodeWithLen(ListNode headA, ListNode headB) {
        // 先获取两个链表长度
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        ListNode p = headA;
        ListNode q = headB;
        // 对比链表长度差让长的链表先移动
        if (lenA > lenB) {
            int gap = lenA - lenB;
            for (int i = 0; i < gap; i++) {
                p = p.next;
            }
        } else {
            int gap = lenB - lenA;
            for (int i = 0; i < gap; i++) {
                q = q.next;
            }
        }

        while (p != null && q != null) {
            if (p == q) return p;

            p = p.next;
            q = q.next;
        }
        return null;
    }

    private int getLength(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 方式2：快慢双指针实现（不需要计算两个链表长度）
     *
     * 提供两个指针p和q分别指向两个链表，短的链表指针p会先走完链表到达节点null后，指针p到另一条链表继续移动
     *
     * 指针q同样走完链表到达节点null后，指针q到另一条链表继续移动
     *
     * 如果链表长度不同，所以肯定会有两个指针相交遇到
     *
     * T:O(m+n)
     * S:O(1)
     */
    private ListNode getIntersectionNodeWithoutLen(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA;
        ListNode q = headB;
        // 但这种可能会存在无限循环的可能，所以题目的前提是一定有相交的节点
        while (p != q) {
            // 链表走到头，切换到其他链表
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
