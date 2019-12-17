package easy;

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

    }

    /**
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

    private static class Link {
        int val;
        Link next;

        Link(int val) {
            this.val = val;
        }
    }
}
