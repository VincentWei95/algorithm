package link.middle;

import link.ListNode;

/**
 * 两数相加：
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 参考：{@link string.AddStrings}
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        AddTwoNumbers main = new AddTwoNumbers();
        main.printLink(main.addTwoNumbers2(l1, l2));
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
     * T:O(max(m,n))，假设 m 和 n 分别表示 l1l1 和 l2l2 的长度，上面的算法最多重复max(m,n) 次
     * S:O(max(m,n)), 新列表的长度最多为max(m,n) + 1
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            cur.next = new ListNode(tmp % 10);

            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    // 扩展：链表顺序高位在链表头
    // 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    // 输出: 7 -> 8 -> 0 -> 7
    private ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 3427 反转l1
        // 4650 反转l2
        // 7087 反转后相加结果
        // 7807 再反转得到最终结果
        ListNode reverseL1 = reverseListNode(l1);
        ListNode reverseL2 = reverseListNode(l2);
        ListNode sumListNode = sumListNode(reverseL1, reverseL2);
        return reverseListNode(sumListNode);
    }

    private ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private ListNode sumListNode(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;

            p.next = new ListNode(tmp % 10);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            p = p.next;
        }
        if (carry > 0) {
            p.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
