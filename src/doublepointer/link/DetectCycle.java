package doublepointer.link;

import java.util.HashSet;
import java.util.Set;

import link.ListNode;

/**
 * 142.环形链表2
 *
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
     *      x
     * -----------------
     * 1 -> 2 -> 3 ->  4  |
     *           |  /   | |
     *         z | 7    5 | y
     *           |  \  /  |
     *           |   6    |
     *
     * 假设从头节点到环形入口节点的距离为 x（即1->2->3->4）
     * 假设最终 fast 和 slow 节点会在节点 5 相遇，环形入口节点到相遇的节点距离为 y（即4->5）
     * 从相遇节点再到环形入口节点的距离为 z（即5->6->7->4）
     *
     * slow 指针会走的节点数为 x + y，fast 指针在环中走的节点数为 n * (y + z)，n 表示 fast 指针走的圈数
     *
     * fast 指针每次走两步，slow 指针每次走一步，即 fast 指针走的节点数是slow指针的两倍
     *
     * 即 (x + y) * 2 = x + y + n * (y + z)
     *
     * 因为要找环形入口，那么就要求 x:
     *
     * (x + y) * 2 = x + y + n * (y + z)
     *
     * 2x + 2y = x + y + n * (y + z)
     *
     * 2x = x + y + n * (y + z) - 2y
     *
     * 2x = x + n * (y + z) - y
     *
     * x = n * (y + z) - y
     *
     * 再从 n * (y + z) 提出一个 (y + z)：
     *
     * x = (n - 1) * (y + z) + z
     *
     * 需要注意 n 一定是 >= 1 的，因为 fast 指针至少要走一圈才能遇到 slow 指针
     *
     * 根据上面的公式，当 n = 1 时，x = z，即 fast 指针在环形里转了一圈之后，就遇到了 slow 指针
     *
     * 意味着从头节点出发一个指针，从相遇节点也出发一个指针，这两个指针每次只走一个节点
     * 当两个指针相遇的时候，就是环形入口
     *
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
                // 头节点和相遇的节点再各自出发一个指针，每个指针都走一步，当相遇时就是环形入口
                ListNode p = head;
                ListNode q = slow;
                while (p != q) {
                    p = p.next;
                    q = q.next;
                }
                return p;
            }
        }
        return null;
    }
}
