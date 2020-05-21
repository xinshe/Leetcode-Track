package m322_CoinChange;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例1:
 *      输入: coins = [1, 2, 5], amount = 11
 *      输出: 3
 *      解释: 11 = 5 + 5 + 1
 *
 * 示例2:
 *      输入: coins = [2], amount = 3
 *      输出: -1
 *
 * 说明:
 *      你可以认为每种硬币的数量是无限的。
 *
 */

public class CoinChange {

    /**
     * 解法1：DP
     *
     * 案例说明：
     *      钞票面值：coins = [1, 2, 5, 7, 10]   金额：14
     *      dp[i]，代表金额 i 的最优解（即最小使用张数）
     *      数组 dp[] 中存储金额1至金额14的最优解（最少使用钞票的数量）。
     *      在计算dp[i]时，dp[0]、dp[l]、dp[2]、…、dp[i-1] 都是已知的
     *      而金额由
     *          金额 i-1 与 coins[0]（1元）组合；
     *          金额 i-2 与 coins[1]（2元）组合；
     *          金额 i-5 与 coins[2]（5元）组合；
     *          金额 i-7 与 coins[3]（7元）组合；
     *          金额 i-10与 coins[4]（10元）组合；
     *
     * 即状态可由状态 i-1、i-2、i-5、i-7、i-10， 5个状态所转移到，故，
     * dp[i] = min（dp[i-1]， dp[i-2]， dp[i-5]， dp[i-7]， dp[i-10]）+ 1
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);    // 最初所有金额的最优解均为可能的最大值+1（即不可达到的值）
        dp[0] = 0;  // 金额0的最优解为0

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i] > dp[i - coin] + 1) { // 不能把数组的初始值设为Integer.MAX_VALUE，不然这里+1的时候会出现整数溢出。
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 解法1的另外一种实现
     */
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1 && dp[i - coin] + 1 < min) {
                    min = dp[i - coin] + 1;
                }
            }
            dp[i] = min;
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
