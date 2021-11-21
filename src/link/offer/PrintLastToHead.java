package link.offer;

import java.util.Arrays;
import java.util.Stack;

import link.ListNode;

/**
 * 剑指offer 06.从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class PrintLastToHead {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);

        PrintLastToHead main = new PrintLastToHead();
        System.out.println(Arrays.toString(main.reversePrint1(head)));
        System.out.println(Arrays.toString(main.reversePrint2(head)));
        System.out.println(Arrays.toString(main.reversePrint3(head)));
    }

    /**
     * 最优：数组
     */
    private int[] reversePrint1(ListNode head) {
        // 先遍历计算链表长度
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int[] array = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            array[i] = head.val; // 数组索引反向存储
            head = head.next;
        }
        return array;
    }

    /**
     * 栈+数组
     *
     * T:O(n)
     * S:O(n)
     */
    private int[] reversePrint2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.add(head.val);
            head = head.next;
        }
        int[] array = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            array[i++] = stack.pop();
        }
        return array;
    }

    /**
     * 链表反转+数组
     *
     * T:O(n)
     * S:O(n)
     */
    private int[] reversePrint3(ListNode head) {
        int size = 0;
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            size++;
        }

        int[] array = new int[size];
        int i = 0;
        while (prev != null) {
            array[i++] = prev.val;
            prev = prev.next;
        }
        return array;
    }
}
