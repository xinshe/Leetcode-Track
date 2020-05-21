package m416_PartitionEqualSubsetSum;

/**
 * 416. 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 *      每个数组中的元素不会超过 100
 *      数组的大小不会超过 200
 *
 * 示例1:
 *      输入: [1, 5, 11, 5]
 *      输出: true
 *      解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例2:
 *      输入: [1, 2, 3, 5]
 *      输出: false
 *      解释: 数组不能分割成两个元素和相等的子集.
 *
 * 题目链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * 参考：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
 */
public class PartitionEqualSubsetSum {

    /**
     * 解法1：二维DP
     *
     * 状态：
     *      dp[i][j]：表示 是否能从数组的 [0, i] 这个子区间内挑选一些正整数（每个数只能用一次），使得这些数的和等于 j。
     *
     * 状态转移方程：dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]], (nums[i] <= j)
     *      如果不选择 nums[i]， 那么 dp[i][j] = dp[i - 1][j] ，表示 是否能在 [0, i - 1] 这个子区间内找到一部分元素，
     *      使得它们的和为 j 。
     *      如果选择 nums[i]，那么 dp[i][j] = dp[i - 1][j - nums[i]]，表示 是否能在 [0, i - 1] 这个子区间内就得找到
     *      一部分元素，使得它们的和为 j - nums[i]。（前提条件是 nums[i] <= j）
     *
     *      1   2   3   4   5   6   7   8   9   10  11 (j)
     * 1    T   F   F   F   F   F   F   F   F   F   F
     * 5    T   F   F   F   T   T   F   F   F   F   F
     * 11   T   F   F   F   T   T   F   F   F   F   T
     * 5    T   F   F   F   T   T   F   F   F   T   T
     * (i)
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int m = sum / 2;
        boolean[][] dp = new boolean[n][m + 1];
        if (nums[0] >= 1 && nums[0] <= m) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j- nums[i]];
                }
            }
        }
        return dp[n - 1][m];
    }

    /**
     * 解法2：一维DP
     *
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
       if (sum % 2 == 1) return false;
       int target = sum >> 1;
       boolean[] dp = new boolean[target + 1];
       if (1 <= nums[0] && nums[0] <= target) dp[nums[0]] = true;
       for (int i = 1; i < nums.length; i++) {
           for (int j = target; j >= nums[i]; j--) {
               dp[j] = dp[j] || dp[j - nums[i]];
           }
       }
       return dp[target];
    }

    /**
     * 解法3：基于解法2，做一些剪枝
     */
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        if (1 <= nums[0] && nums[0] <= target) dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= target) {
                dp[target] = dp[target] || dp[target - nums[i]];
            }
            if (dp[target]) return true;
            for (int j = target - 1; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }


}
