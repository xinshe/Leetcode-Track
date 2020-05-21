package m518_CoinChange2;

/**
 * 518. 零钱兑换 II
 *
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 *
 * 示例1:
 *      输入: amount = 5, coins = [1, 2, 5]
 *      输出: 4
 *      解释: 有四种方式可以凑成总金额:
 *          5=5
 *          5=2+2+1
 *          5=2+1+1+1
 *          5=1+1+1+1+1
 *
 * 示例2:
 *      输入: amount = 3, coins = [2]
 *      输出: 0
 *      解释: 只用面额2的硬币不能凑成总金额3。
 *
 * 示例3:
 *      输入: amount = 10, coins = [10]
 *      输出: 1
 *  
 *
 * 注意:
 * 你可以假设：
 *      0 <= amount (总金额) <= 5000
 *      1 <= coin (硬币面额) <= 5000
 *      硬币种类不超过 500 种
 *      结果符合 32 位符号整数
 *
 */
public class CoinChange2 {

    /**
     * 解法1：DP
     *
     * 状态：dp[i] 当amount = i时的组合数
     *
     * 状态转移方程：dp[i] = dp[i] + dp[i - coin]
     *
     * 边界状态条件：当没有硬币时，如果要组成金额0，那么组合数为1；如果要组成其他正数金额，那么组合数为0。即：
     *      dp[0] = 1，其他情况 dp[i] = 0
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (amount < 0 || coins == null || coins.length == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) { // 物品
            for (int i = coin; i <= amount; i++) { // target
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
