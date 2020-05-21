package m2_AddTwoNumbers;

import helper.ListNode;

/**
 * 2. �������
 *
 * ���������ǿյ�����������ʾ�����Ǹ������������У����Ǹ��Ե�λ���ǰ�������ķ�ʽ�洢�ģ��������ǵ�ÿ���ڵ�
 * ֻ�ܴ洢һλ���֡�
 * ��������ǽ��������������������᷵��һ���µ���������ʾ���ǵĺ͡�
 *
 * �����Լ���������� 0 ֮�⣬����������������0��ͷ��
 *
 * ʾ����
 * ���룺(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * �����7 -> 0 -> 8
 * ԭ��342 + 465 = 807
 *
 * ���ӣ�https://leetcode-cn.com/problems/add-two-numbers
 */

public class AddTwoNumbers {

	/**
	 * �Լ��Ľⷨ��
	 * ����ⷨ���Լ���һ���뵽�ģ��տ�ʼ�����ǽ�ÿ������ת���ɶ�Ӧ��������Ȼ���������������ӵõ��ͣ��ٽ���ת������������ⷨ�������Ŀ����϶���
	 * �в�ͨ�ģ���Ϊ����ĳ���δ֪�����ܻ�ܳ����������������������ܱ����������ݷ�Χ��
	 * ���Ծ��뵽���������ֽⷨ����Ϊ�����е�����˳����ʵ������˳�����෴�ģ��������и�λ����ǰ�棬��λ����������档���Ժ���Ȼ��һ��˼·���Ǵ�����ͷ
	 * ����ʼ�����������ζ����������ÿһλ��ӣ����õ��Ľ���ĸ�λ�������ڽ�������У����������9�ͽ�1�����뵽��һλ�ļӷ����С�
	 * ���ֽⷨ��һ�����������������ĵ�һλ�ĺ���Ϊ��ʼֵ�������ӵڶ�λ��ʼ���ڵ�һ�����м����ʱ���ÿ�������Ϊ�յ��������Ϊ��Ŀ��˵���������ǿ�
	 * ���������������Ĳ�����Ҫֱ�ӶԴ�����������Ҫ�᷵�ؽ����������в�����������һ������ָ��p���в���.
	 * ��������һ������Ϊ���ˣ�����Ҫ��������Ƿ�Ҫ��1���жϣ�����299999��8����Ϊ���ܻ�һֱҪ��1.
	 * ���о�����������Ϊ���ˣ���Ҫ�������һ���Ƿ��1���жϣ�����8��2
	 * ����ⷨ������һ����������˼·��
	 * 
	 * �������ֽⷨ�кܶ�ֵ�øĽ��ĵط���
	 * 1���ܲ���ֱ�Ӵӵ�һλ��ʼ����������
	 * 2���ظ�����ܶ࣬���´���ܳ����ܲ��ܷŵ�һ��ѭ���н��д�������
	 * 3���򻯴���
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
		if (carry == 1) { // ע�⣺��һ�����ױ�����
			p.next = new ListNode(1);
		}
		return head.next;
	}

	// ����Ĵ���̫��������һ�¼�
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
