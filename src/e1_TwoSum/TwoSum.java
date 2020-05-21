package e1_TwoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. ����֮��
 *
 * ����һ���������� nums ��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ�����������������������ǵ������±ꡣ
 *
 * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�
 *
 * ʾ��:
 * 		���� nums = [2, 7, 11, 15], target = 9
 * 		��Ϊ nums[0] + nums[1] = 2 + 7 = 9
 * 		���Է��� [0, 1]
 *
 * https://leetcode-cn.com/problems/two-sum/
 */

public class TwoSum {

	// ע����ⲻ����˫ָ����߶��ֲ��ң���Ϊ�������������λ�þͱ䶯��

	/**
	 * ����1�������ⷨ
	 *
	 * ʱ�临�Ӷȣ�O(N^2)
	 * �ռ临�Ӷȣ�O(1)
	 */
	public static int[] twoSum1(int[] nums, int target) {
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
	 * ����2�������ϣ��
	 *
	 * ʱ�临�Ӷȣ�O(N)
	 * �ռ临�Ӷȣ�O(N)
	 */
	public static int[] twoSum2(int[] nums, int target) {
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
	 * ����3��һ���ϣ���Ƽ���
	 *
	 * ʱ�临�Ӷȣ�O(N)
	 * �ռ临�Ӷȣ�O(N)
	 */
	public static int[] twoSum3(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) { // <Ԫ��ֵ, ����λ��>
			int complement = target - nums[i];
			if(map.containsKey(complement))
				return new int[] {map.get(complement),i};
			map.put(nums[i], i);
		}
		return null;
	}

	public static void main(String[] args) {
		int[] nums = {3,3};
		int[] res = twoSum3(nums,6);
		System.out.println(res[0] + "," + res[1]);
	}

}
