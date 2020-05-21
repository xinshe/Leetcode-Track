package h115_DistinctSubsequences;

/**
 * 115. 不同的子序列
 *
 * 给定一个字符串S和一个字符串T，计算在 S 的子序列中 T 出现的个数。
 *
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 示例 1:
 * 输入: S = "rabbbit", T = "rabbit"
 * 输出: 3
 * 解释:
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2:
 * 输入: S = "babgbag", T = "bag"
 * 输出: 5
 * 解释:
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 */

public class DistinctSubsequences {

    /**
     * 解法1：DP
     *
     * 状态：
     *      dp[i][j] 代表 T 前 i 字符串可以由 S 前 j 字符串组成最多个数.
     *
     * 状态转移方程:
     *      当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
     *      当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
     *
     * 状态边界条件：
     *      对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1
     *      对于第一列, S 为空,这样组成 T 个数当然为 0 了
     *
     * S = "babgbag", T = "bag"
     * 列一个二维table，如下：
     *
     *       ""  b   a   b   g   b   a   g
     *   ""  1   1   1   1   1   1   1   1
     *   b   0   1   1   2   2   3   3   3
     *   a   0   0   1   1   1   1   4   4
     *   g   0   0   0   0   1   1   1   5
     */
    public int numDistinct(String s, String t) {
        if (t == null || s == null) return 0;
        if (t.equals("")) return 1;
        if (s.equals("")) return 0;
        int m = t.length();
        int n = s.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
