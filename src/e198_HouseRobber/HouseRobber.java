package e198_HouseRobber;

/**
 * 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互
 * 连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 题目链接：https://leetcode-cn.com/problems/house-robber/
 */

public class HouseRobber {

    /**
     * dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
     */
    public static int rob(int[] nums) {
        int len = nums.length;
        if (0 == len) return 0;
        if (1 == len) return nums[0];
        if (2 == len) return nums[0] > nums[1] ? nums[0] : nums[1];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = dp[0] > nums[1] ? dp[0] : nums[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    /**
     * 考虑到 dp[i] 只与 dp[i - 1] 和 dp[i - 2] 有关，因此可以只用两个变量来存储
     * dp[i - 1] 和 dp[i - 2]，使得原来的 O(N) 空间复杂度优化为 O(1) 复杂度。
     */
    public static int rob2(int[] nums) {
        int pre1 = 0, pre2 = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,1};
        int[] arr2 = new int[]{2,7,9,3,1};
        int[] arr3 = new int[]{};
        int[] arr4 = new int[]{1};
        int[] arr5 = new int[]{1,2};
        System.out.println(rob(arr1));//4
        System.out.println(rob(arr2));//12
        System.out.println(rob(arr3));
        System.out.println(rob(arr4));
        System.out.println(rob(arr5));
    }
}
