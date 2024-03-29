package link.replenish;

import link.ListNode;

/**
 * 21.合并两个有序链表：
 *
 * 给你两个递增排序的链表，你要把它们合成一个链表，并且保持递增排序。另外要求，新链表上的节点使用的就是旧的两个链表上的节点，不能创建新节点
 *
 * 比如说，给你的两个链表 L1 和 L2，分别是：
 *
 * L1: 1 -> 3
 *
 * L2: 2 -> 4 -> 6
 *
 * 合并后的链表就是：
 *
 * 1 -> 2 -> 3 -> 4 -> 6
 *
 * 或者：
 *
 * L1：1 -> 2 -> 4
 *
 * L2：1 -> 3 -> 4
 *
 * 合并后的链表就是：
 *
 * 1 -> 1 -> 2 -> 3 -> 4 -> 4
 */
public class MergeTwoSortLink {

    public static void main(String[] args) {
        ListNode link1 = new ListNode(1);
        link1.next = new ListNode(3);
        ListNode link2 = new ListNode(2);
        link2.next = new ListNode(4);
        link2.next.next = new ListNode(6);

        MergeTwoSortLink main = new MergeTwoSortLink();
        ListNode mergeLink = main.mergeTwoSortLink(link1, link2);
        System.out.println(main.printLink(mergeLink));
    }

    private String printLink(ListNode link) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(link.val);
            link = link.next;
            if (link == null) break;
            sb.append("->");
        }
        return sb.toString();
    }

    /**
     * 先循环两个链表，对比两个链表各自节点的值，将节点重新拼接
     *
     * T:O(m+n)
     * S:O(1)
     */
    private ListNode mergeTwoSortLink(ListNode link1, ListNode link2) {
        ListNode dummy = new ListNode(0), p = dummy; // 空链表头

        while (link1 != null && link2 != null) {
            // 循环两个链表位置的值，链表头指向对应链表，链表位置往后移动
            if (link1.val < link2.val) {
                p.next = link1;
                link1 = link1.next;
            } else {
                p.next = link2;
                link2 = link2.next;
            }
            p = p.next; // 每次链表头都往后移动
        }

        // 其中有一个链表为null，链表头指向剩下不为null的链表
        if (link1 != null) p.next = link1;
        if (link2 != null) p.next = link2;

        return dummy.next;
    }
}
