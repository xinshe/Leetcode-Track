package h25_ReverseNodesInKGroup;

import helper.ListNode;

/**
 * 25. K 个一组翻转链表
 *
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k=2 时，应当返回: 2->1->4->3->5
 * 当 k=3 时，应当返回: 3->2->1->4->5
 *
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 题目链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        for (int i = 0; i < k - 1 && p != null; i++) {  // k个元素一组，p指针只需要移动 k-1 次
            p = p.next;
        }
        if (p == null) {
            return head;
        }
        ListNode remain = p.next;
        p.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(remain, k);
        return newHead;
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = new ListNode(0);
        ListNode p = node;
        while (p != null) {
            ListNode next = newHead.next;
            newHead.next = p;
            p = p.next;
            newHead.next.next = next;
        }
        return newHead.next;
    }
}
