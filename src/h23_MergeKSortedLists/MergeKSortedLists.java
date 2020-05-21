package h23_MergeKSortedLists;

import helper.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 参考：https://blog.csdn.net/L__ear/article/details/89095537?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 */
public class MergeKSortedLists {

    /**
     * 解法1：最大堆
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        if (lists == null || lists.length == 0) {
            return head.next;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }

        ListNode p = head;
        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            if (minNode.next != null) {
                heap.add(minNode.next);
            }
            p.next = minNode;
            minNode.next = null;
            p = p.next;
        }
        return head.next;
    }

    /**
     * 解法2：分治
     */
    public ListNode mergeKLists02(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left >= right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }
}
