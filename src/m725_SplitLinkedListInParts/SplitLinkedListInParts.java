package m725_SplitLinkedListInParts;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode root, int k) {
        // 首先得到链表元素的个数n
        int n = 0;
        ListNode cur = root;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int mod = n % k; // 余数
        int size = n / k; // 分隔后的每一个链表的基础大小
        int curSize; // 分隔后的每一个链表的实际大小，实际大小 = 基础大小 + 1|0（前mod个为1，其余为0）
        ListNode[] ret = new ListNode[k];
        ListNode tmp;
        cur = root;
        for (int i = 0; cur != null && i < k; i++) { // 这里 cur != null 一定要加，因为后面的链表可能分不到元素，为空。
            ret[i] = cur;
            curSize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 1; j < curSize; j++) {
                cur = cur.next; // 这里只需要跳 curSize - 1 步即可，走到分隔处的前面一个节点
            }
            tmp = cur.next;
            cur.next = null; // 分隔，断开连接
            cur = tmp;
        }
        return ret;
    }
}
