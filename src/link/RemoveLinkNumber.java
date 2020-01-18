package link;

/**
 * 单链表删除数字：
 *
 * 给你一个单链表和一个数字，你要删除节点上数字等于给定数字的那些节点，然后返回删除节点后的单链表
 *
 * 比如说，给你的单链表是：
 *
 * 1 -> 2 -> 4 -> 1 -> 8 -> 1
 *
 * 要删除的数字是 1。那么删除 1 后，你要返回的单链表是：
 *
 * 2 -> 4 -> 8
 */
public class RemoveLinkNumber {

    public static void main(String[] args) {

    }

    /**
     *
     * 定义节点dummy作为最终结果，开始指向第一个节点，也定义一个节点p用来往后追踪
     * dummy->1->2->4->1->8->1
     * p->1->2->4->1->8->1
     *
     * 假设删除的节点是1，第一个就遇到数字1，节点p指向删除节点后面的数字p.next = p.next.next
     * p->2->4->1->8->1
     *
     * 第二个和第三个没有遇到相同的数字，更新节点p指向下一个节点p = p.next
     * p->2->4->1->8->1
     *
     * 第四个遇到相同的数字，节点p指向删除节点后面的数字p.next = p.next.next
     * p->2->4->8->1
     *
     * 第五个没有遇到相同的数字，更新节点p指向下一个节点p = p.next
     *
     * 第六个遇到相同的数字，节点p指向删除节点后面的数字p.next = p.next.next
     * p->2->4->8
     *
     * 链表没有其他节点，返回结果
     *
     * T:O(n)
     * S:O(1)
     */
    private Link removeNumber(Link head, int num) {
        Link dummy = new Link(0);
        dummy.next = head;
        Link p = dummy;

        while (p.next != null) {
            if (p.next.val == num) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return dummy.next;
    }
}
