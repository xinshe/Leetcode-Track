package m2_AddTwoNumbers;

import helper.ListNode;

/**
 * 2. 两数相加
 *
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点
 * 只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以0开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */

public class AddTwoNumbers {

	/**
	 * 自己的解法：
	 * 这个解法是自己第一下想到的，刚开始做的是将每个链表转换成对应的整数，然后把这两个整数相加得到和，再将和转换成链表。这个解法在这道题目里面肯定是
	 * 行不通的，因为链表的长度未知，可能会很长，超出基本数据类型所能保存的最大数据范围。
	 * 所以就想到了下面这种解法，因为链表中的数的顺序与实际数的顺序是相反的，即链表中个位数在前面，高位数在链表后面。所以很自然的一个思路就是从链表头
	 * 部开始遍历链表，依次对两个链表的每一位相加，将得到的结果的个位数保存在结果链表中，若结果大于9就进1，参与到下一位的加法当中。
	 * 这种解法第一步首先算出了两链表的第一位的和作为初始值，遍历从第二位开始。在第一步进行计算的时候不用考虑链表为空的情况，因为题目中说了是两个非空
	 * 的链表。另外对链表的操作不要直接对传入的链表或者要会返回结果的链表进行操作，可设置一个操作指针p进行操作.
	 * 就算另外一个链表为空了，还是要对其进行是否要进1的判断，比如299999和8，因为可能会一直要进1.
	 * 还有就算两个链表都为空了，还要对其进行一次是否进1的判断，比如8和2
	 * 这个解法借助了一般快速排序的思路。
	 * 
	 * 但是这种解法有很多值得改进的地方：
	 * 1、能不能直接从第一位开始遍历？？？
	 * 2、重复代码很多，导致代码很长，能不能放到一个循环中进行处理？？？
	 * 3、简化代码
	 * 
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null || (l1.next == null && l1.val == 0)) {
			return l2;
		}
		if (l2 == null || (l2.next == null && l2.val == 0)) {
			return l1;
		}
		ListNode head = new ListNode(0);
		ListNode p = head;
		ListNode p1 = l1;
		ListNode p2 = l2;
		int carry = 0;
		while (p1 != null && p2 != null) {
			int sum = p1.val + p2.val + carry;
			carry = sum / 10;
			p.next = new ListNode(sum % 10);
			p = p.next;
			p1 = p1.next;
			p2 = p2.next;
		}
		while (p1 != null) {
			int sum = p1.val + carry;
			carry = sum / 10;
			p.next = new ListNode(sum % 10);
			p = p.next;
			p1 = p1.next;
		}
		while (p2 != null) {
			int sum = p2.val + carry;
			carry = sum / 10;
			p.next = new ListNode(sum % 10);
			p = p.next;
			p2 = p2.next;
		}
		if (carry == 1) { // 注意：这一步容易被忽略
			p.next = new ListNode(1);
		}
		return head.next;
	}

	// 上面的代码太长，可做一下简化
	public static ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
		if (l1 == null || (l1.next == null && l1.val == 0)) {
			return l2;
		}
		if (l2 == null || (l2.next == null && l2.val == 0)) {
			return l1;
		}
		ListNode head = new ListNode(0);
		ListNode p = head;
		ListNode p1 = l1;
		ListNode p2 = l2;
		int carry = 0;
		while (p1 != null || p2 != null) {
			int x = p1 == null ? 0 : p1.val;
			int y = p2 == null ? 0 : p2.val;
			int sum = x + y + carry;
			carry = sum / 10;
			p.next = new ListNode(sum % 10);
			p = p.next;
			if (p1 != null) p1 = p1.next;
			if (p2 != null) p2 = p2.next;
		}
		if (carry == 1) {
			p.next = new ListNode(1);
		}
		return head.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(8);

		ListNode l2 = new ListNode(1);

		ListNode res = addTwoNumbers(l1,l2);
		while(res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
