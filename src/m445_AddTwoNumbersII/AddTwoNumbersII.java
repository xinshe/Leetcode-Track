package m445_AddTwoNumbersII;

import helper.ListNode;

import java.util.Stack;

/**
 * 445. 两数相加 II
 *
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。
 * 将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶:
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例:
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 *
 */

public class AddTwoNumbersII {

    /**
     * 方法1：反转链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode head = new ListNode(-1);
        ListNode p = head;
        int addOne = 0;
        while (l1 != null && l2 != null) {
            int cur = l1.val + l2.val + addOne;
            if (cur >= 10) {
                cur %= 10;
                addOne = 1;
            } else {
                addOne = 0;
            }
            p.next = new ListNode(cur);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            if (addOne == 0) {
                p.next = l1;
                break;
            } else {
                int cur = l1.val + addOne;
                if (cur >= 10) {
                    cur %= 10;
                    addOne = 1;
                } else {
                    addOne = 0;
                }
                p.next = new ListNode(cur);
                p = p.next;
                l1 = l1.next;
            }

        }
        while (l2 != null) {
            if (addOne == 0) {
                p.next = l2;
                break;
            } else {
                int cur = l2.val + addOne;
                if (cur >= 10) {
                    cur %= 10;
                    addOne = 1;
                } else {
                    addOne = 0;
                }
                p.next = new ListNode(cur);
                p = p.next;
                l2 = l2.next;
            }
        }
        if (addOne != 0) {
            p.next = new ListNode(addOne);
        }
        return reverseList(head.next);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode newHead = new ListNode(-1);
        while (p != null) {
            ListNode tmp = newHead.next;
            newHead.next = p;
            p = p.next;
            newHead.next.next = tmp;
        }
        return newHead.next;
    }


    /**
     * 方法2：用两个栈
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);
        ListNode head = new ListNode(-1);
        int num1, num2, sum;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) { // 当两个栈都为空时，如果上一位的运算还有进1，即carry!=0时，需要再进行一次计算
            num1 = stack1.isEmpty() ? 0 : stack1.pop();
            num2 = stack2.isEmpty() ? 0 : stack2.pop();
            sum = num1 + num2 + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
        }
        return head.next;
    }

    private Stack<Integer> buildStack(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }
}
