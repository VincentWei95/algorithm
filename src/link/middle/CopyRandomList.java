package link.middle;

public class CopyRandomList {

    /**
     * T:O(n)
     * S:O(1)
     *
     * 题解：https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 节点1的随机节点指向节点3
        // 节点3的随机指针指向节点2
        // 节点2的随机指针指向空

        // 在每个原节点后面创建一个新节点
        // 1'、2'、3'为新节点
        // 1->1'->2->2'->3->3'
        Node p = head;
        while (p != null) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = p.next.next;
        }

        // 设置新节点的随机节点
        // 原节点1的随机指针指向原节点3，新节点1的随机指针指向的是原节点3的 next
        // 原节点3的随机指针指向原节点2，新节点3的随机指针指向的是原节点2的 next
        // 结论：
        // 原节点 i 的随机指针（如果有的话），指向的是原节点 j
        // 新节点 i 的随机指针，指向的是原节点 j 的 next
        p = head;
        while (p != null) {
            if (p.random != null) {
                // p.next 是新节点
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        // 将两个链表分离
        Node dummyHead = new Node(-1);
        p = head;
        Node cur = dummyHead;
        while (p != null) {
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummyHead.next;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
