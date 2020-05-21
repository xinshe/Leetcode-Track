package m148_SortList;

/**
 * 时间复杂度 O(NlogN)—— merge函数为O(N)
 * 空间复杂度 O(1)
 * 稳定
 */
public class SortListBottom2Up {

    public ListNode sortList(ListNode head) {
        // 0 or 1 element
        if (head == null || head.next == null) return head;

        // get the linked list's length
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // left是左边部分的第一个节点，right是右边部分的第一个节点，tail是右边部分最后一个节点（或者理解为
        // 上一个排序部分的最后一个节点），cur是当前排序部分的第一个节点，所以要把cur赋给left
        ListNode left, right, tail;
        for (int sz = 1; sz < length; sz <<= 1) {
            cur = dummy.next;
            tail = dummy;   // 需要tail将每次merge的结果串起来
            while (cur != null) {
                left = cur;
                right = split(left, sz);
                cur = split(right, sz);
                tail = merge(left, right, tail);
            }
        }
        return dummy.next;
    }

    /**
     * Divide the linked list into two lists,
     * while the left list contains first n nodes
     * return the right list's head
     */
    private ListNode split(ListNode head, int n) {
        while (--n != 0 && head != null) head = head.next;  // 经过这个循环后，head为左边部分的最后一个节点
        if (head != null) {
            ListNode right = head.next; // right为右边部分的第一个节点
            head.next = null;   // 切断左右两部分
            return right;   // 返回右边部分的头结点
        }
        return null;
    }

    /**
     * merge the two sorted linked list left and right,
     * then append the merged sorted linked list to the node head
     * return the tail of the merged sorted linked list
     */
    private ListNode merge(ListNode left, ListNode right, ListNode head) {
        ListNode p = head;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        p.next = (left != null) ? left : right;
        while (p.next != null) p = p.next;
        return p;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
