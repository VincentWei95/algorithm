package link;

import java.util.HashSet;
import java.util.Set;

/**
 * 141.判断链表是否有环：
 *
 * 给你一个单链表，你要判断它是否会形成环，也就是链表的最后一个节点指向了前面一个已经存在的节点
 *
 * 比如给你链表：
 *
 * 1 -> 2 -> 4 -> 8
 *
 * 链表没有环，返回false
 *           8
 *        |   |
 * 1 ->  2 -> 4
 *
 * 链表最后指向2形成一个环，返回true
 */
public class LinkHasCycle {

    public static void main(String[] args) {
        ListNode link = new ListNode(1);
        link.next = new ListNode(2);
        link.next.next = new ListNode(4);
        link.next.next.next = new ListNode(8);
        link.next.next.next.next = link.next.next;

        LinkHasCycle main = new LinkHasCycle();
        System.out.println(main.hasCyclePointer(link));
    }

    /**
     * 方式1：Set记录是否遇到相同的Link
     *
     * T:O(n)
     * S:O(n)
     */
    private boolean hasCycleHashSet(ListNode link) {
        Set<ListNode> set = new HashSet<>();
        for (ListNode p = link; p != null; p = p.next) {
            if (set.contains(p)) return true;
            set.add(p);
        }

        return false;
    }

    /**
     * 方式2：快慢指针实现
     *
     * 快指针每次走两步，慢指针每次走一步，对比快慢指针指向的节点值是否相同，如果有环快指针肯定有机会追到慢指针
     *
     * T:O(n)
     * S:O(1)
     */
    private boolean hasCyclePointer(ListNode link) {
        ListNode f = link;
        ListNode s = link;
        while (f != null && f.next != null) {
            f = f.next.next; // 快指针走两步
            s = s.next; // 慢指针走一步
            if (f == s) return true;
        }
        return false;
    }
}
