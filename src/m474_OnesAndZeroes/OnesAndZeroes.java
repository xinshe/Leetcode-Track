package m474_OnesAndZeroes;

/**
 * 474. 一和零
 *
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 *
 * 注意:
 *      给定 0 和 1 的数量都不会超过 100。
 *      给定字符串数组的长度不会超过 600。
 *
 * 示例1:
 *      输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 *      输出: 4
 *      解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 *
 * 示例2:
 *      输入: Array = {"10", "0", "1"}, m = 1, n = 1
 *      输出: 2
 *      解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 *
 */
public class OnesAndZeroes {

    /**
     * 解法1：DP
     *
     * 这是一个多维费用的 0-1 背包问题，有两个背包大小，0 的数量和 1 的数量。
     *
     * 状态：dp[i][j] 表示使用i个0和j个1，最多能拼出的字符串数目
     *
     * 状态转移方程：
     *      dp[i][j] = max(dp[i][j], dp[i - countZero(strs(k))][j - countOne(strs(k))])
     *
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) return 0;
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0;
            int ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
