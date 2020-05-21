package m24_SwapNodesInPairs;

import helper.ListNode;

/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 题目要求：不能修改结点的 val 值，O(1) 空间复杂度。
 *
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */

public class SwapNodesInPairs {

    /**
     * 解法1：非递归
     */
    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode p = newHead;
        ListNode tmp, first, second;
        while (p.next != null && p.next.next != null) {
            first = p.next;
            second = p.next.next;
            tmp = second.next;

            p.next = second;
            second.next = first;
            first.next = tmp;

            p = first;
        }
        return newHead.next;
    }

    /**
     * 解法2：递归
     */
    public ListNode swapPairs2(ListNode head) {
       if (null == head || null == head.next) return head;
       ListNode second = head.next;
       head.next = swapPairs2(second.next);
       second.next = head;
       return second;
    }
}
