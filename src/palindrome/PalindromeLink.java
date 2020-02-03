package palindrome;

import link.ListNode;

import java.util.Stack;

/**
 * 判断单链表是否为回文链表：
 *
 * 给你一个单链表表示的数，你要判断它是不是一个回文数字。回文数字就是正着读和反着读都相同的数字。
 *
 * 比如说，给你的链表是：
 *
 * 4 -> 2
 *
 * 它表示 42，反过来是 24，不是一个回文数字，因此你要返回 false。
 *
 * 再比如说，给你的链表是：
 *
 * 4 -> 2 -> 2 -> 4
 *
 * 它表示 4224，反过来也是 4224，它是一个回文数字，因此你要返回 true。
 */
public class PalindromeLink {

    public static void main(String[] args) {
        PalindromeLink main = new PalindromeLink();
        ListNode head = new ListNode(4);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(4);
        head.next = second;
        second.next = third;
        third.next = fourth;
        System.out.println(main.isPalindromeLinkRevert(head));
    }

    /**
     * 方式1：先遍历一遍单链表（因为单链表只能从头到尾），将字符存储起来，然后转成字符串用回文字符串的方式对比实现
     *
     * T:O(n)
     * S:O(n) 需要使用StringBuilder
     */
    private boolean isPalindromeLinkStr(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            current = current.next;
        }

        String str = sb.toString();
        int left = 0;
        int right = str.length() - 1;
        for (; left < right; left++, right--) {
            if (str.charAt(left) == str.charAt(right)) continue;
            return false;
        }
        return true;
    }

    /**
     * 方式2：遍历链表入栈，然后再循环链表和弹栈的值对比
     *
     * T:O(n)
     * S:O(n) 需要使用栈
     */
    private boolean isPalindromeLinkStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        for (ListNode p = head; p != null; p = p.next) {
            stack.push(p.val);
        }

        for (ListNode p = head; p != null; p = p.next) {
            if (p.val != stack.pop()) return false;
        }
        return true;
    }

    /**
     * 方式3：方式2的优化方案，反转链表提高空间复杂度
     *
     * 4 -> 2 -> 2 -> 4
     *
     * 反转一半链表，4 <- 2  2 -> 4
     *
     * 4 -> 2 -> 1 -> 2 -> 4
     *
     * 反转一半链表，4 <- 2 <- 1  2 -> 4
     *
     * T:O(n)
     * S:O(1)
     */
    private boolean isPalindromeLinkRevert(ListNode head) {
        // 计算链表节点个数
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) {
            ++len;
        }

        // 反转一半链表
        ListNode cur = head;
        ListNode pre = null;
        for (int i = 0; i < len / 2; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 如果是奇数，还要拿下个节点
        if (len % 2 == 1) cur = cur.next;

        for (; pre != null && cur != null; pre = pre.next, cur = cur.next) {
            if (pre.val != cur.val) return false;
        }
        return true;
    }
}
