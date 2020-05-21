package e206_ReverseLinkedList;

import helper.ListNode;

/**
 * 206. 反转链表
 *
 * Reverse a singly linked list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

public class ReverseLinkedList {

    /**
     * 解法1：头插法
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode newHead = new ListNode(0);
        while (p != null) {
            ListNode tmp = newHead.next;
            newHead.next = p;
            p = p.next; // 这行代码一定要放在上下这两行之间
            newHead.next.next = tmp;
        }
        return newHead.next;
    }

    /**
     * 解法2：递归
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next); // head.next遍历到最后一个节点，head遍历到倒数第二个节点
        head.next.next = head; // 改变指向
        head.next = null; // 删除原来的指向
        return newHead;
    }

}
