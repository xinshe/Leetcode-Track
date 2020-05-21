package e83_RemoveDuplicatesFromSortedList;

import helper.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 *
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 *
 */

public class RemoveDuplicatesFromSortedList {

    /**
     * 方法1：迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 方法2：迭代（另一种实现）
     */
    public ListNode deleteDuplicates02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode p = head.next;
        while (p != null) {
            while (p != null && cur.val == p.val) {
                p = p.next;
            }
            cur.next = p;
            if (p != null) {
                cur = p;
                p = cur.next;
            }
        }
        return head;
    }

    /**
     * 方法3：递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode deleteDuplicates03(ListNode head) {
        if (null == head || null == head.next) return head;
        head.next = deleteDuplicates03(head.next); // 到倒数第二个节点
        return head.val == head.next.val ? head.next : head;
    }
}
