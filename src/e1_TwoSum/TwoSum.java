package e1_TwoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 题目链接：https://leetcode-cn.com/problems/two-sum
 */

public class TwoSum {

// 注意此题中的数组不能排序，即不能用双指针或者二分查找，因为数组排序后索引位置就变动了

	/**
	 * 方法1：暴力解法
	 *
	 * 时间复杂度：O(N^2)
	 * 空间复杂度：O(1)
	 */
	public int[] twoSum1(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		int len = nums.length;
		for(int i = 0; i < len; i++) {
			for(int j = i + 1; j < len; j++) {
				if(nums[i] == target - nums[j])
					return new int[]{i,j};
			}
		}
		return null;
	}

	/**
	 * 方法2：两遍哈希表
	 *
	 * 时间复杂度：O(N)
	 * 空间复杂度：O(N)
	 */
	public int[] twoSum2(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int len = nums.length;
		for(int i = 0; i < len; i++) {
			map.put(nums[i], i);
		}
		
		for(int i = 0; i < len; i++) {
			int complement = target - nums[i];
			if(map.containsKey(complement) && map.get(complement) != i)
				return new int[]{i,map.get(complement)};
		}
		
		return null;
	}

	/**
	 * 方法2：一遍哈希表（推荐）
	 *
	 * 时间复杂度：O(N)
	 * 空间复杂度：O(N)
	 */
	public int[] twoSum3(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) { // <元素值，索引位置>
			int complement = target - nums[i];
			if(map.containsKey(complement))
				return new int[] {map.get(complement),i};
			map.put(nums[i], i);
		}
		return null;
	}

}
