package link.replenish;

import link.ListNode;

/**
 * 876.链表的中间节点
 *
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 *
 * 示例2：
 *
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * 提示：
 *
 * 给定链表的结点数介于1和100之间。
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        MiddleNode middleNode = new MiddleNode();
        System.out.println(middleNode.printLink(middleNode.middleNode1(head)));
        System.out.println(middleNode.printLink(middleNode.middleNode2(head)));
        System.out.println(middleNode.printLink(middleNode.middleNode3(head)));
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
     * 遍历一遍计算长度并将节点数值存储到数组中，获取中间节点使用 size / 2
     *
     * T:O(n)
     * S:O(n) 需要申请一个数组长度
     */
    private ListNode middleNode1(ListNode head) {
        ListNode[] list = new ListNode[100]; // 节点数最多有提示为100
        int size = 0;
        while (head != null) {
            list[size++] = head;
            head = head.next;
        }
        return list[size / 2];
    }

    /**
     * 单指针法：遍历一遍计算长度，再遍历一次链表到 size / 2
     *
     * T:O(n)
     * S:O(1)
     */
    private ListNode middleNode2(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head;
        int k = 0;
        while (k < size / 2) {
            k++;
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 快慢指针法：快指针走两步，慢指针走一步，当快指针走完时，慢指针刚好达到中间位置
     *
     * T:O(n)
     * S:O(1)
     */
    private ListNode middleNode3(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
