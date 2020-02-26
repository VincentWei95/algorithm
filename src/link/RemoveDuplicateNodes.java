package link;

import java.util.HashSet;
import java.util.Set;

/**
 * 移除重复节点：
 *
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点
 *
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 */
public class RemoveDuplicateNodes {

    public static void main(String[] args) {

    }

    /**
     * T:O(n)
     * S:O(n)
     */
    private ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode cur = head;
        while (cur.next != null) {
            if (set.add(cur.next.val)) {
                cur = cur.next;
            } else {
                cur.next = cur.next.next;
            }
        }
        return head;
    }
}
