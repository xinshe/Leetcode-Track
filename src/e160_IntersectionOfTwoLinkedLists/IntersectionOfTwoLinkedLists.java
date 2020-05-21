package e160_IntersectionOfTwoLinkedLists;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意：
 *   如果两个链表没有交点，返回 null.
 *   在返回结果后，两个链表仍须保持原有的结构。
 *   可假定整个链表结构中没有循环。
 *   程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 参考：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
 */

import helper.ListNode;

/**
 * 若存在交点，两个指针在相遇的时候走过的路程分别为 a+c+b 和 b+c+a，路程一样，速度一样，必定时间也一样
 * 反之，若不存在交点，那么循环退出时，两个指针走过的路程分别为 a+b 和 b+a，此时两个指针都走到了尽头，指向为null
 */
public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}
