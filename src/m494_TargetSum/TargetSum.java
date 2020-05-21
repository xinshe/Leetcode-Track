package m494_TargetSum;

/**
 * 494. 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数 S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，
 * 你都可以从 + 或 - 中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例1:
 *      输入: nums: [1, 1, 1, 1, 1], S: 3
 *      输出: 5
 *      解释:
 *          -1+1+1+1+1 = 3
 *          +1-1+1+1+1 = 3
 *          +1+1-1+1+1 = 3
 *          +1+1+1-1+1 = 3
 *          +1+1+1+1-1 = 3
 *          一共有5种方法让最终目标和为3。
 *
 * 注意:
 *      数组非空，且长度不会超过20。
 *      初始的数组的和不会超过1000。
 *      保证返回的最终结果能被32位整数存下。
 *
 */
public class TargetSum {

    /**
     * 解法1：DP（二维数组）
     *
     * 状态：dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。
     *
     * 状态转移方程：
     *      考虑第 i 个数 nums[i]，它可以被添加 + 或 -
     *      dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S) return 0;
        int n = nums.length;
        int m = sum * 2 + 1;    // 添加正负号以后，数组中所有元素的和最大为sum，最小为-sum。
        int[][] dp = new int[n][m];
        dp[0][nums[0] + sum] = 1;
        dp[0][-nums[0] + sum] += 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 2 * sum; j++) {    // 把和调到 [0, 2*sum]，就相当于对每个j加了一个sum
                if (j - nums[i] >= 0)
                    dp[i][j] += dp[i - 1][j - nums[i]];
                if (j + nums[i] <= 2 * sum)
                    dp[i][j] += dp[i - 1][j + nums[i]];
            }
        }
        return dp[n - 1][S + sum]; // 为了使数组下标不为负，都加上一个sum
    }

    /**
     * 解法2：DP（一维数组）
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S) return 0;
        int n = nums.length;
        int m = 2 * sum + 1;
        int[] dp = new int[m];
        dp[nums[0] + sum] = 1;
        dp[-nums[0] + sum] += 1;
        for (int i = 1; i < n; i++) {
            int[] next = new int[m]; // 为了防止值被覆盖，所以要开辟一个临时数组保存更新后的值
            for (int j = 0; j <= 2 * sum; j++) {
                if (j - nums[i] >= 0) next[j] += dp[j - nums[i]];
                if (j + nums[i] <= 2 * sum) next[j] += dp[j + nums[i]];
            }
            dp = next;
        }
        return dp[S + sum];
    }

    /**
     * 解法3：将该问题转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
     *
     * 记在前面加正号的所有的数构成集合P，在前面加负号的所有的数构成集合N，那么下面等式一定成立，
     * sum(P) - sum(N) = S
     * 同时在等式两边加上数组和 sum (sum = sum(P) + sum(N))
     * sum(P) + sum(N) + sum(P) - sum(N) = S + sum(P) + sum(N)
     * 2 * sum(P) = S + sum
     * sum(P) = (S + sum) / 2
     * 所以原问题就等价于在数组中找到一组数，使得它们的和等于(S + sum) / 2，求这样的一组数的数目
     *
     * 下面是二维DP的实现：
     */
    public int findTargetSumWays3(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (S + sum) % 2 == 1) return 0;
        int target = (S + sum) / 2;
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        // 边界状态一定要考虑清楚
        dp[0][0] = 1;   // dp[0][0]表示数组的第一个数构成和为0的情况的次数，那么即为：不选第一个数的情况
        if (nums[0] <= target) dp[0][nums[0]] += 1; // 选择第一个数的情况，注意这里是要累加
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];    // 不选择nums[i]的情况
                if (j >= nums[i]) {
                    dp[i][j] += dp[i - 1][j - nums[i]]; // 选择nums[i]的情况，注意这里是要累加
                }
            }
        }
        return dp[n - 1][target];
    }

    /**
     * 解法4：解法3的一维DP实现
     */
    public int findTargetSumWays4(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 == 1) return 0;
        int target = (sum + S) >> 1;
        int[] dp = new int[target + 1];
        dp[0] = 1;  // 不选第一个数的情况
        if (nums[0] <= target) dp[nums[0]] += 1;    // 选第一个数的情况
        for (int i = 1; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {   // 因为数组只有一维，所以只用考虑[nums[i], target]之间的情况
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    /**
     * 解法5：DFS
     */
    public int findTargetSumWays5(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }

    private int findTargetSumWays(int[] nums, int start, int S) {
        if (start == nums.length) {
            return S == 0 ? 1 : 0;
        }
        return findTargetSumWays(nums, start + 1, S + nums[start])
                + findTargetSumWays(nums, start + 1, S - nums[start]);
    }
}
