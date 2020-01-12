package link;

/**
 * 链表的相交节点：
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
        Link link1 = new Link(1);
        link1.next = new Link(2);
        link1.next.next = new Link(6);
        link1.next.next.next = new Link(7);

        Link link2 = new Link(3);
        link2.next = new Link(4);
        link2.next.next = new Link(5);
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
    private Link getIntersectionNodeWithLen(Link headA, Link headB) {
        // 先获取两个链表长度
        int lenA = 0;
        int lenB = 0;
        for (Link p = headA; p != null; p = p.next) {
            lenA++;
        }
        for (Link q = headB; q != null; q = q.next) {
            lenB++;
        }

        Link p = headA;
        Link q = headB;
        // 对比链表长度差让长的链表先移动
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                p = p.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                q = q.next;
            }
        }

        // 对比是否相同
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
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
    private Link getIntersectionNodeWithoutLen(Link headA, Link headB) {
        if (headA == null || headB == null) return null;
        Link p = headA;
        Link q = headB;
        while (p != q) {
            // 链表走到头，切换到其他链表
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
