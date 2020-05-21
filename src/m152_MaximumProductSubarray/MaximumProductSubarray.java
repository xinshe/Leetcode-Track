package m152_MaximumProductSubarray;

/**
 * 152. 乘积最大子序列
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int curMax = 1;
        int curMin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) { // 为什么当前元素小于0就要交换？因为小于0 的数要乘上一个较小的数，如果是乘上较大的数，只会更小
                int tmp = curMax;
                curMax = curMin;
                curMin = tmp;
            }
            curMax = Math.max(nums[i], curMax * nums[i]);
            curMin = Math.min(nums[i], curMin * nums[i]);
            max = Math.max(curMax, max);
        }
        return max;
    }
}
