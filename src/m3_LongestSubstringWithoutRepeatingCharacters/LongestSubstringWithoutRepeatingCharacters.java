package m3_LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */

public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * 解法1：滑动窗口
	 *
	 * 时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 left 和 right 访问两次。
	 * 空间复杂度：O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k) 的空间，其中 k 表示 Set 的大小。而 Set 的大小取决于
	 * 字符串 n 的大小以及字符集 / 字母 m 的大小。
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
	 * 解法2：优化的滑动窗口（推荐）
	 *
	 * 时间复杂度：O(n)，索引 j 将会迭代 n 次。
	 * 空间复杂度（HashMap）：O(min(m, n))，与之前的方法相同
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
				left = Math.max(map.get(cur) + 1, left); // 注意：要比较一下取最大值。因为重复出现的元素位置可能会在left的左边
			}
			res = Math.max(res, right - left + 1); // 注意：加1
			map.put(cur, right++); // 加入新元素 或者 覆盖旧的已存在的元素
		}
		return res;
	}

	/**
	 * 题目是要找到给定字符串 的 最长子串的长度。要注意两个地方：第一，题目要求子串没有重复字符；
	 * 第二，子串不同于子序列，子串要求是连续的
	 * 
	 * 我的解决思路如下：
	 * 对给定字符串s进行遍历，sub保存遍历到当前位置时的子串，tmp保存遍历到当前位置的字符，index为tmp在当前子串sub中的索引，
	 * 如果index!=-1，则表明sub中含有tmp，进行如下处理：从子串sub中index位置开始，向前删除所有字符（包括index位置）
	 * 然后将当前字符tmp追加到sub中
	 * 最后，与maxLen进行比较大小，以进行取舍
	 */
	public int lengthOfLongestSubstring03(String s) {
		StringBuffer sub = new StringBuffer();
		int maxLen = 0;
		for(int i = 0; i < s.length(); i++) {
			String tmp = s.substring(i, i+1);	// 获取第i个位置的字符
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
