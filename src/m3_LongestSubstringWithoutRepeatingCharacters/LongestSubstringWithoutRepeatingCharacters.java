package m3_LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. ���ظ��ַ�����Ӵ�
 *
 * ����һ���ַ����������ҳ����в������ظ��ַ�����Ӵ��ĳ��ȡ�
 *
 * ʾ��1:
 * ����: "abcabcbb"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "abc"�������䳤��Ϊ 3��
 *
 * ʾ�� 2:
 * ����: "bbbbb"
 * ���: 1
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "b"�������䳤��Ϊ 1��
 *
 * ʾ�� 3:
 * ����: "pwwkew"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ���"wke"�������䳤��Ϊ 3��
 *     ��ע�⣬��Ĵ𰸱����� �Ӵ� �ĳ��ȣ�"pwke"��һ�������У������Ӵ���
 *
 * ���ӣ�https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */

public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * �ⷨ1����������
	 *
	 * ʱ�临�Ӷȣ�O(2n) = O(n)��������������£�ÿ���ַ����� left �� right �������Ρ�
	 * �ռ临�Ӷȣ�O(min(m, n))����֮ǰ�ķ�����ͬ���������ڷ���Ҫ O(k) �Ŀռ䣬���� k ��ʾ Set �Ĵ�С���� Set �Ĵ�Сȡ����
	 * �ַ��� n �Ĵ�С�Լ��ַ��� / ��ĸ m �Ĵ�С��
	 *
	 */
	public int lengthOfLongestSubstring01(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) {
			return 0;
		}
		Set<Character> set = new HashSet<>();
		int left = 0;
		int right = 0;
		int res = 0;
		while (left < len && right < len) {
			Character cur = s.charAt(right);
			if (!set.contains(cur)) {
				set.add(cur);
				right++;
				res = Math.max(res, right - left);
			} else {
				set.remove(s.charAt(left++));
			}
		}
		return res;
	}

	/**
	 * �ⷨ2���Ż��Ļ������ڣ��Ƽ���
	 *
	 * ʱ�临�Ӷȣ�O(n)������ j ������� n �Ρ�
	 * �ռ临�Ӷȣ�HashMap����O(min(m, n))����֮ǰ�ķ�����ͬ
	 *
	 */
	public int lengthOfLongestSubstring02(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		int left = 0, right = 0;
		int res = 0;
		while (right < len) {
			char cur = s.charAt(right);
			if (map.containsKey(cur)) {
				left = Math.max(map.get(cur) + 1, left); // ע�⣺Ҫ�Ƚ�һ��ȡ���ֵ����Ϊ�ظ����ֵ�Ԫ��λ�ÿ��ܻ���left�����
			}
			res = Math.max(res, right - left + 1); // ע�⣺��1
			map.put(cur, right++); // ������Ԫ�� ���� ���Ǿɵ��Ѵ��ڵ�Ԫ��
		}
		return res;
	}

	/**
	 * ��Ŀ��Ҫ�ҵ������ַ��� �� ��Ӵ��ĳ��ȡ�Ҫע�������ط�����һ����ĿҪ���Ӵ�û���ظ��ַ���
	 * �ڶ����Ӵ���ͬ�������У��Ӵ�Ҫ����������
	 * 
	 * �ҵĽ��˼·���£�
	 * �Ը����ַ���s���б�����sub�����������ǰλ��ʱ���Ӵ���tmp�����������ǰλ�õ��ַ���indexΪtmp�ڵ�ǰ�Ӵ�sub�е�������
	 * ���index!=-1�������sub�к���tmp���������´������Ӵ�sub��indexλ�ÿ�ʼ����ǰɾ�������ַ�������indexλ�ã�
	 * Ȼ�󽫵�ǰ�ַ�tmp׷�ӵ�sub��
	 * �����maxLen���бȽϴ�С���Խ���ȡ��
	 */
	public int lengthOfLongestSubstring03(String s) {
		StringBuffer sub = new StringBuffer();
		int maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			String tmp = s.substring(i, i+1);	// ��ȡ��i��λ�õ��ַ�
			int index = sub.indexOf(tmp);
			if(index != -1) {
				while(index >= 0)
					sub.deleteCharAt(index--);
			}
			sub.append(tmp);
			maxLen = maxLen < sub.length() ? sub.length() : maxLen;
		}
		return maxLen;
	}

}
