package e53_MaximumSubarray;

/**
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *      输入: [-2,1,-3,4,-1,2,1,-5,4],
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为6。
 *
 * 进阶:
 *      如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class MaximumSubarray {

    /**
     * 解法1：DP
     *
     * 原问题：求整个数组的一个连续子数组的和最大。子问题：求部分数组的一个连续子数组的和最大。
     * 状态：dp[i] 表示以第i个元素为结尾的连续子数组的最大和
     * 状态转移方程：dp[i] = Max(dp[i-1], 0) + nums[i] （也就是说取决于它的上一个状态值是否大于0）
     * 状态边界条件：dp[0] = nums[0]
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
