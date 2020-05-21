package h123_BestTimetoBuyandSellStockIII;

/**
 * 123. 买卖股票的最佳时机 III
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例1:
 *      输入: [3,3,5,0,0,3,1,4]
 *      输出: 6
 *      解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
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
 *      解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://blog.csdn.net/qq_29592167/article/details/81207153
 * https://blog.csdn.net/laputafallen/article/details/79846424
 */
public class BestTimetoBuyandSellStockIII {

    /**
     * 解法1：
     * 大体上就是有限的几天内找一个点，这个点之前买卖的最高收益加上这点之后买卖的最高收益就是全局最优解，
     * 相应的，一个顺序记录最高收益，一个倒序记录最高收益，最后寻找对应的最大和，思路简单粗暴却非常有效
     * 比如：
     *          3 3 5 0 0 3 1 4
     * forward  X 0 2 2 2 3 3 4
     * backward 4 4 4 4 4 3 3 X
     *
     * forward[i]表示第0至i天内进行交易能获得的最大收益，backward[i]表示第i至(len-1)天进行交易能获得的最大收益
     * 这里就有一个疑问，第i天会不会出现冲突？因为可能会出现在第i天卖出又买进的情况。实际上如果出现这种情况就等价
     * 于只做了一次交易，而题目限制了最多做两次交易，所以做一次交易也是可以的。比如，
     * 第一次交易：在第x天买进，在第i天卖出，收益为prices[i]-prices[x]
     * 第二次交易：在第j天买进，在第y天卖出，收益为prices[y]-prices[j]
     * 如果i=j的话，总收益prices[i]-prices[x] + prices[y]-prices[j] = prices[y]-prices[x]
     * 实际上就相当于 只做了一次交易：在第x天买进，在第y天卖出
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxProfit(int[] prices) {
        int len;
        if (null == prices || (len = prices.length) <= 1) return 0;
        int[] forward = new int[len];
        int[] backward = new int[len];
        int profit = 0;
        int minPrice = prices[0];
        int maxPrice = prices[len - 1];
        for (int i = 1; i < len; i++) {
            forward[i] = Math.max(forward[i - 1], prices[i] - minPrice); // 第0至i天内进行交易能获得的最大收益
            minPrice = Math.min(minPrice, prices[i]);
        }
        for (int i = len - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i + 1], maxPrice - prices[i]);  // 第i至(len-1)天进行交易能获得的最大收益
            maxPrice = Math.max(maxPrice, prices[i]);
        }
        for (int i = 0; i < len; i++) {
            profit = Math.max(profit, forward[i] + backward[i]);
        }
        return profit;
    }

    /**
     * 解法2：
     * 思路如下：
     * 开始的金钱资本是0元，首次买入股票会损失掉一定的钱数，假设第i天买入的，持有资本就是buy1 = -prices[i]，
     * 当第j天卖出，第一次交易之后，持有的收益就是 sell1 =（-prices[i]+prices[j]），第二次买入假设第k天买入，
     * 现在持有的资本是sell1，buy2 = sell1-prices[k]，相应的，假设第m天，第二次交易结束，持有的收益
     * sell2 = buy2+price[m]，以此类推
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit2(int[] prices) {
        int len;
        if (null == prices || (len = prices.length) <= 1) return 0;
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        for (int i = 0; i < len; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, prices[i] + buy2);
        }
        return sell2;
    }

    /**
     * 解法3：DP
     *
     * k = 2 和前面121、123题的情况稍微不同，因为上面的情况都和 k 的关系不太大。要么 k 是正无穷，状态转移和 k 没关系了；
     * 要么 k = 1，跟 k = 0 这个 base case 挨得近，最后也没有存在感。
     *
     * 这一道题与LeetCode 188题都是要对k进行处理。
     *
     * 状态：dp[i][k][1] 第i天，现在手上持有股票，至今最多进行k次交易，获得的最大收益
     *
     * 状态转移方程：
     *      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *      dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int[][][] dp = new int[n][2+1][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n - 1][2][0];
    }
}
