package link;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除排序链表中的重复元素：
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次
 *
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        DeleteDuplicates main = new DeleteDuplicates();
        System.out.println(main.deleteDuplicates(head));
    }

    /**
     * 对比当前和下一个链表节点的值，如果相同则当前节点指向下下个节点，否则继续按正常方式往下执行，最终返回头指针
     *
     * T:O(n)
     * S:O(1)
     */
    private ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    /**
     * 最简单处理方式：
     *
     * 首次遍历筛选存储不重复的链表节点，二次循环重新构建一个新链表
     */
    private ListNode deleteDuplicatesSet(ListNode head) {
        Set<Integer> set = new HashSet<>();
        while (head != null) {
            if (!set.contains(head.val)) {
                set.add(head.val);
            }
            head = head.next;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        for (Integer val : set) {
            ListNode newLink = new ListNode(val);
            p.next = newLink;
            p = p.next;
        }
        return dummyHead.next;
    }
}
