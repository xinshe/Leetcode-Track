package m19_RemoveNthNodeFromEndOfList;

import helper.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */

public class RemoveNthNodeFromEndOfList {

    /**
     * 一次遍历
     * 这里题目中写了n始终是合法的，若没有这个限制，还需要对n的边界条件进行考虑
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head || n <= 0) return null;
        ListNode p1 = head;
        while (n-- > 0) {
            p1 = p1.next;
        }
        if (p1 == null) return head.next;   // 若n=链表长度，p1在这里会为空，所以直接删除第一个元素即可，返回head.next
        p1 = p1.next; // 因为要删除倒数第n个元素，所以需要找到倒数第n+1个元素，这样才能方便删除，所以这里让p1多走一步，即n+1步
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return head;
    }

    /**
     * 一次遍历的另外一种实现（推荐）
     */
    public ListNode removeNthFromEnd02(ListNode head, int n) {
        if (head == null && n <= 0) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode p1 = newHead;
        ListNode p2 = newHead;

        while (n-- >= 0 && p1 != null) { // 这里从n到0，一共后移了n+1位，因为必须要获得待删除的节点的前面一个节点，才能删除待删除的节点
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;

        return newHead.next;
    }
}
