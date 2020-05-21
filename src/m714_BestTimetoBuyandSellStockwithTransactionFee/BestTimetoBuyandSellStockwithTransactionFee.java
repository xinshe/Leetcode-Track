package m714_BestTimetoBuyandSellStockwithTransactionFee;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 示例1:
 *      输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 *      输出: 8
 *      解释: 能够达到的最大利润:
 *          在此处买入 prices[0] = 1
 *          在此处卖出 prices[3] = 8
 *          在此处买入 prices[4] = 4
 *          在此处卖出 prices[5] = 9
 *          总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 *      0 < prices.length <= 50000.
 *      0 < prices[i] < 50000.
 *      0 <= fee < 50000.
 *
 */
public class BestTimetoBuyandSellStockwithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (null == prices || prices.length <= 1) return 0;
        int rest = 0;
        int hold = -50000;
        for (int price : prices) {
            int preRest = rest;
            rest = Math.max(preRest, hold + price - fee);
            hold = Math.max(hold, preRest - price);
        }
        return rest;
    }

    /**
     * 解法2：通用DP解法
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态转移方程：
     *      dp[i][k][0] = max(dp[i][k][0], dp[i-1][k][1] + prices[i] - fee)
     *      dp[i][k][1] = max(dp[i][k][1], dp[i-1][k-1][0] - prices[i])
     *
     *      由于不限交易次数，所以状态转移方程可以简化为：
     *      dp[i][0] = max(dp[i][0], dp[i-1][1] + prices[i] - fee)
     *      dp[i][1] = max(dp[i][1], dp[i-1][0] - prices[i])
     *
     *      由于第i天的状态只与第i-1天的状态相关，所以状态转移方程可以继续简化为：
     *      dp_i_0 = max(dp_i_0, dp_i_1 + prices[i] - fee)
     *      dp_i_1 = max(dp_i_1, dp_i_0 - prices[i]) // 这里的dp_i_0不能用上面的更新值
     */
    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) return 0;
        // 第0天的状态
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i] - fee);
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
        }
        return dp_i_0;
    }
}
