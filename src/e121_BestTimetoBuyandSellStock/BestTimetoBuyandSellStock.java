package e121_BestTimetoBuyandSellStock;

/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例1:
 *      输入: [7,1,5,3,6,4]
 *      输出: 5
 *      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例2:
 *      输入: [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 参考：
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
 * https://www.bilibili.com/video/av31637852?from=search&seid=15347889203287479383
 */

public class BestTimetoBuyandSellStock {

    /**
     * 解法1：
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 1) return 0;
        int curMinPrice = Integer.MAX_VALUE;
        int curMaxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (curMinPrice > prices[i]) curMinPrice = prices[i];
            else curMaxProfit = Math.max(curMaxProfit, prices[i] - curMinPrice);
        }
        return curMaxProfit;
    }

    /**
     * 解法2：DP
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态边界值：注意k = 0 和 i = -1这两种情况
     *      dp[-1][k][0] = dp[i][0][0] = 0
     *      dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移方程：
     *      dp[i][1][0] = max(dp[i - 1][1][0], dp[i - 1][1][1] + price[i])  // rest or sell
     *      dp[i][1][1] = max(dp[i - 1][1][1], dp[i - 1][0][0] - price[i])  // rest or buy
     *
     *      可化简为：
     *      dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + price[i])
     *      dp[i][1] = max(dp[i - 1][1], - price[i])
     *
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 由于第i天的状态只与第i-1天的状态有关，所以可以继续简化
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        // 边界状态
        int dp_i_0 = 0; // 第0天不持有股票
        int dp_i_1 = -prices[0];  // 第0天持有股票
        for (int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }



}
