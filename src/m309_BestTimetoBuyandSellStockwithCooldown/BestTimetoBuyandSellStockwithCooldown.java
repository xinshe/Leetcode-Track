package m309_BestTimetoBuyandSellStockwithCooldown;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *      你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *      卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 *      输入: [1,2,3,0,2]
 *      输出: 3
 *      解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 参考：
 * [1] https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
 * [2] 状态转移方程的图示很赞！！！
 * https://www.bilibili.com/video/av31578180?from=search&seid=16954776974413170506
 */

public class BestTimetoBuyandSellStockwithCooldown {

    /**
     * 解法1：
     * 根据花花酱的视频里的状态转移图示，可以得出以下状态转移方程
     * rest[i] = max(rest[i-1], sold[i-1])
     * hold[i] = max(hold[i-1], rest[i-1]-prices[i])
     * sold[i] = hold[i-1]+prices[i]
     */
    public int maxProfit(int[] prices) {
        int len;
        if (null == prices || (len = prices.length) <= 1) return 0;
        int[] rest = new int[len];
        int[] hold = new int[len];
        int[] sold = new int[len];

        rest[0] = 0;
        hold[0] = -prices[0];
        sold[0] = 0;

        for (int i = 1; i < len; i++) {
            rest[i] = Math.max(rest[i-1], sold[i-1]);
            hold[i] = Math.max(hold[i-1], rest[i-1]-prices[i]);
            sold[i] = hold[i-1] + prices[i];
        }

        return Math.max(sold[len-1], rest[len-1]);
    }

    /**
     * 解法2：
     *
     * 观察以下三个状态转移方程：
     * rest[i] = max(rest[i-1], sold[i-1])
     * hold[i] = max(hold[i-1], rest[i-1]-prices[i])
     * sold[i] = hold[i-1]+prices[i]
     * 可以知道，rest、hold、sold这三个状态只与 第i步和第i-1步有关，所以空间复杂度可以由O(n)降到O(1)
     * 设置rest、hold、sold三个变量即可。
     * 但是需要注意的是，在这三个变量中，应该选择一个变量保存它之前的一个值，就像下面方法的循环中的三种情况一样，选择
     * 任意一个变量保存之前的值都是可以的。
     */
    public int maxProfit2(int[] prices) {
        if (null == prices || prices.length <= 1) return 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        int sold = 0;
        for (int price : prices) {
//            int preSold = sold;
//            sold = hold + price;
//            hold = Math.max(hold, rest - price);
//            rest = Math.max(rest, preSold);

//            int preRest = rest;
//            rest = Math.max(preRest, sold);
//            hold = Math.max(hold, preRest - price);
//            sold = hold + price;

            int preHold = hold;
            hold = Math.max(preHold, rest - price);
            rest = Math.max(rest, sold);
            sold = preHold + price;
        }
        return Math.max(rest, sold);
    }

    /**
     * 解法3：通用DP解法
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态转移方程：
     *      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *      dp[i][k][1] = max(dp[i-1][k][1], dp[i-2][k-1][0] - prices[i])
     *
     *      由于不限交易次数，所以状态转移方程可以简化为：
     *      dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *      dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     *
     *      由于第i天的状态只与第i-1天和第i-2天的状态相关，所以状态转移方程可以继续简化为：
     *      dp_i_0 = max(dp_i_0. dp_i_1 + prices[i])
     *      dp_i_1 = max(dp_i_1, dp_pre_0 - prices[i])
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        // 第0天的状态
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        int dp_pre_0 = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = tmp;
        }
        return dp_i_0;
    }
}
