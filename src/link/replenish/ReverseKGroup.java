package link.replenish;

import link.ListNode;

/**
 * 25.k个一组翻转链表
 *
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例1：
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 1 -> 2 -> 3 -> 4 -> 5  =>  2 -> 1 -> 4 -> 3 -> 5
 *
 * 示例2：
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 1 -> 2 -> 3 -> 4 -> 5 => 3 -> 2 -> 1 -> 4 -> 5
 *
 * 示例3：
 *
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例4：
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 */
public class ReverseKGroup {

    /**
     * 1、定义哑节点dummyHead，定义两个节点prev和end，prev和end初始时都指向dummyHead
     * 		1  ->   2  ->  3  ->  4  ->  5
     * prev
     * end
     *
     * 2、end走k步到达待反转的链尾节点，获取后继节点next=end.next，如果next=null，直接退出循环；获取反转的链头节点start=prev.next
     *	  	1  ->   2  ->  3  ->  4  ->  5
     * prev  start
     * 			   end    next
     *
     * 3、断开节点end.next=null，prev.next=null，传入start反转链表
     * 	  	1  ->   2      3  ->  4  ->  5
     * prev  start
     * 			   end    next
     *
     * 4、反转完后，prev.next=end，start.next=next重新连接链表；prev=start，end=start准备下个区域的链表翻转
     * 	  	2  ->   1      3  ->  4  ->  5
     * prev  	  start
     *     end    	     next
     *
     *	  	2  ->   1  ->  3  ->  4  ->  5
     * prev       start
     *     end    		 next
     *
     *	  	2  ->   1  ->  3  ->  4  ->  5
     * 	  		   prev
     *  	   	   end
     * 继续按上述步骤操作，直到next=end.next=null，跳出循环
     *
     * T:O(n)
     * S:O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        ListNode end = dummyHead;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;

            ListNode start = prev.next;
            ListNode next = end.next;

            end.next = null;
            prev.next = null;

            reverse(start);

            prev.next = end;
            start.next = next;

            prev = start;
            end = start;
        }

        return dummyHead.next;
    }

    private void reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
}
