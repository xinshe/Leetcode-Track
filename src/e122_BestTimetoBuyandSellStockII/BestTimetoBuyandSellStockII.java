package e122_BestTimetoBuyandSellStockII;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1:
 *      输入: [7,1,5,3,6,4]
 *      输出: 7
 *      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例2:
 *      输入: [1,2,3,4,5]
 *      输出: 4
 *      解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例3:
 *      输入: [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://www.bilibili.com/video/av46044121?from=search&seid=5528458437059913978
 */

public class BestTimetoBuyandSellStockII {

    /**
     * 解法1
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 1) return 0;
        int total = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                total += prices[i] - prices[i - 1];
        }
        return total;
    }

    /**
     * 解法2：DP
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态转移方程：
     *      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *      dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     *      因为此题不限交易次数，所以与k无关，状态转移方程可以简化为：
     *      dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *      dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     *      又因为第i天的状态只与第i-1天的状态有关，所以又可以简化为与i无关的状态转移方程
     *      dp_i_0 = max(dp_i_0, dp_i_1 + prices[i])
     *      dp_i_1 = max(dp_i_1, dp_i_0 - prices[i]) // 这里用到的 dp_i_0 应该是没有被更新的，所以前面应该要用一个临时变量存dp_i_0
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        // 第0天的状态
        int dp_i_0 = 0; // 不持有股票
        int dp_i_1 = -prices[0]; // 持有股票
        for (int i = 1; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

}
