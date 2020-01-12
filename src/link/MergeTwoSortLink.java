package link;

/**
 * 合并两个有序链表：
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
 */
public class MergeTwoSortLink {

    public static void main(String[] args) {
        Link link1 = new Link(1);
        link1.next = new Link(3);
        Link link2 = new Link(2);
        link2.next = new Link(4);
        link2.next.next = new Link(6);

        MergeTwoSortLink main = new MergeTwoSortLink();
        Link mergeLink = main.mergeTwoSortLink(link1, link2);
        System.out.println(main.printLink(mergeLink));
    }

    private String printLink(Link link) {
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
     * T:O(m+n)
     * S:O(1)
     */
    private Link mergeTwoSortLink(Link link1, Link link2) {
        Link dummy = new Link(0), p = dummy; // 空链表头

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
