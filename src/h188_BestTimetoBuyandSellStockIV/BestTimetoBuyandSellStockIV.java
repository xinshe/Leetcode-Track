package h188_BestTimetoBuyandSellStockIV;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1:
 *      输入: [2,4,1], k = 2
 *      输出: 2
 *      解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例2:
 *      输入: [3,2,6,5,0,3], k = 2
 *      输出: 7
 *      解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 */

public class BestTimetoBuyandSellStockIV {

    /**
     * 解法1：一维数组DP
     * 思路：当k>=len/2时，问题就退化成了可以交易任意次了(LeetCode 122)，所以只要将任意两天股票差大于0的加起
     * 来即可; 当k<len/2时，可以记录k次交易每次买进之后和卖出之后能获得的最大利润（累计）：
     * 1、第i次买进之后，剩下的最大利润为第(i-1)次卖出股票之后的利润－当前的股票价格．状态转移方程为：
     * 　　　　buy[i] = max(sell[i-1] - curPrice, buy[i]);
     * 2、第i次卖出之后，剩下的最大利润为第i次买操作之后剩下的利润＋当前股票价格．状态转移方程为：
     * 　　　　sell[i] = max(buy[i] + curPrice, sell[i]);
     *
     * 时间复杂度：O(n*k)
     * 空间复杂度：O(n)
     *
     * 参考：https://blog.csdn.net/qq508618087/article/details/51678245
     */
    public int maxProfit(int k, int[] prices) {
        int len;
        if (null == prices || (len = prices.length) <= 1 || k <= 0) return 0;
        if (k >= len >> 1) {
            int ans = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i - 1])
                    ans += (prices[i] - prices[i - 1]);
            }
            return ans;
        }
        int[] buy = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            buy[i] = Integer.MIN_VALUE;
        }
        int[] sell = new int[k + 1];
        for (int curPrice : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(sell[i - 1] - curPrice, buy[i]);
                sell[i] = Math.max(buy[i] + curPrice, sell[i]);
            }
        }
        return sell[k];
    }

    /**
     * 解法2：二维数组DP
     * 典型的动态规划，并且利用局部和全局最优.
     * 定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优
     * 定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优（最后一天可能不卖出）
     *
     * 局部最优的递推式：local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
     * 分两种情况来分析局部最优的递推式：
     * 1、在第i-1天时，正在进行第j次交易（也就是说，现在手中有买进的一支股票尚未卖出），所以最后一天必须将股
     * 票卖出，而且这是算在第j次交易当中的，这种情况下，local[i - 1][j] + diff，只需将i-1天的局部最优解加上
     * 最后一天卖出的差值即可，相当于将最后一次交易多延迟了一天
     * 2、在第i-1天时已经进行j-1次交易，产生了一个全局最优解global[i-1][j-1]，在最后一天还得进行一次交易，
     * 且必须卖出，如果diff大于0，那么就在第i-1天买进，第i天卖出；如果小于0，就直接在第i天买进又卖出，
     * 只是为了满足j次交易，就相当于没有交易，即加上0就可以了。
     *
     * 全局最优的递推式：global[i][j] = max(local[i][j], global[i - 1][j])
     *
     * 参考：https://www.jianshu.com/p/26f792f83ee4
     */
    public int maxProfit2(int k, int[] prices) {
        int len;
        if (null == prices || (len = prices.length) <= 1 || k <= 0) return 0;
        if (k >= len >> 1) {
            int ans = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i - 1])
                    ans += (prices[i] - prices[i - 1]);
            }
            return ans;
        }

        int[][] global = new int[len][k+1];
        int[][] local = new int[len][k+1];
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i-1][j-1] + Math.max(diff, 0), local[i-1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i-1][j]);
            }
        }
        return global[len-1][k];
    }

    /**
     * 解法3：DP（股票交易问题通用DP解法）
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态转移方程：
     *      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *      dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
    public int maxProfit3(int k, int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        if (k > n >> 1) {   // k比较大的情况，可能会超出内存限制，所以要单独处理。也就是相当于k没有限制
            int ans = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1]) {
                    ans += (prices[i] - prices[i-1]);
                }
            }
            return ans;
        }
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[n-1][k][0];
    }

}
