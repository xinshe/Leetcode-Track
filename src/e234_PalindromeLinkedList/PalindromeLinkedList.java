package e234_PalindromeLinkedList;

import helper.ListNode;

/**
 * 234. 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 题目链接：https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 */

public class PalindromeLinkedList {

    /**
     * 方法1：
     * 直接把元素插入数组中，在数组中比较。
     */

    /**
     * 方法2：切成两半，把后半段反转，然后比较两半是否相等。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) { // 空链表也是回文串
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 奇数情况下：前半段：[head, slow]     后半段：[slow, fast] 如 1->2->3
        // 偶数情况下：前半段：[head, slow-1]   后半段：[slow, fast) 如 1->2
        ListNode rightHead = reverseList(slow);

        ListNode p1 = head;
        ListNode p2 = rightHead;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        ListNode p = head;
        while (p != null) {
            ListNode next = newHead.next;
            newHead.next = p;
            p = p.next;
            newHead.next.next = next;
        }
        return newHead.next;
    }
}
