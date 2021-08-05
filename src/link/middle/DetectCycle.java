package link.middle;

import java.util.HashSet;
import java.util.Set;

import link.ListNode;

/**
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 示例1：
 *
 * 3 -> 2 -> 0 -> -4
 *      |         |
 *      - - - - - -
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例2：
 *
 * 1 -> 2
 * |    |
 * - - -
 *
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例1：
 *
 * 1
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 */
public class DetectCycle {

    public static void main(String[] args) {

    }

    /**
     * T:O(n)
     * S:O(n)
     */
    private ListNode detectCycleHash(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * T:O(n)
     * S:O(1)
     */
    private ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // 从相遇点到入环点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离
                // 当快慢指针相遇时，将p指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }
}
