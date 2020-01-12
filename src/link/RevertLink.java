package link;

/**
 * 反转单链表：
 *
 * 给你一个单链表，你需要反转它，然后返回。
 *
 * 比如说给你的单链表是：
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * 你要返回的反转后的链表是：
 *
 * 5 -> 4 -> 3 -> 2 -> 1 -> null
 */
public class RevertLink {

    public static void main(String[] args) {
        RevertLink main = new RevertLink();
        Link head = new Link(1);
        Link second = new Link(2);
        Link third = new Link(3);
        Link fourth = new Link(4);
        Link fifth = new Link(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        System.out.println(main.printLink(head));
        System.out.println(main.printLink(main.revertLink(head)));
    }

    private String printLink(Link head) {
        StringBuilder sb = new StringBuilder();
        for (Link p = head; p != null; p = p.next) {
            sb.append(p.val).append("->");
        }
        sb.append("null");
        return sb.toString();
    }

    /**
     * 初始：
     * null -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     * pre    cur
     *
     * Link next = current.next;
     *       next = 2
     * null -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     * pre    cur
     *
     * current.next = previous;
     * null <- 1  2 -> 3 -> 4 -> 5 -> null
     * pre    cur
     *
     * previous = current;
     * null <- 1  2 -> 3 -> 4 -> 5 -> null
     *        pre
     *
     * current = next;
     * null <- 1  2 -> 3 -> 4 -> 5 -> null
     *        pre cur
     *
     * T:O(n) 遍历一次链表
     * S:O(1)
     */
    private Link revertLink(Link head) {
        Link current = head;
        Link previous = null;
        while (current != null) {
            Link next = current.next; // 把当前指向的位置先拿出来
            current.next = previous; // 当前的下一个指向上一个
            previous = current; // 上一个变成了当前
            current = next; // 当前就是刚把指向的位置拿出来的link
        }
        return previous;
    }
}
