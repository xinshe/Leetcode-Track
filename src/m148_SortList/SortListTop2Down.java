package m148_SortList;

/**
 * 时间复杂度 O(NlogN)—— merge函数为O(N)
 * 空间复杂度 O(logN) —— 因为用到了递归，递归空间为logN
 * 稳定
 */
public class SortListTop2Down {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // step 1. cut the list to two halves
        ListNode slow = head;   // 使用快指针和慢指针找到mid节点
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;   // mid为右边部分的第一个节点，slow是左边部分的最后一个节点
        slow.next = null;   // 将左右部分断开

        // step 2. sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // step 3. merge left and right
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);   // 结果保存在以dummy节点为头的链表中
        ListNode p = dummy; // p为操作节点，指向当前插入处
        while (left != null && right != null) { // 将left和right中，节点值较小的节点插入到结果链表
            if (left.val <= right.val) {    // 这里取等号，维持排序稳定性
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if (left != null) p.next = left;    // 将剩余部分插入到结果链表
        if (right != null) p.next = right;
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
