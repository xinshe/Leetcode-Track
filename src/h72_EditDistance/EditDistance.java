package h72_EditDistance;

/**
 * 72. 编辑距离
 *
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数。
 *
 * 你可以对一个单词进行如下三种操作：
 *      插入一个字符
 *      删除一个字符
 *      替换一个字符
 *
 * 示例1:
 *      输入: word1 = "horse", word2 = "ros"
 *      输出: 3
 *      解释:
 *          horse -> rorse (将 'h' 替换为 'r')
 *          rorse -> rose (删除 'r')
 *          rose -> ros (删除 'e')
 *
 * 示例2:
 *      输入: word1 = "intention", word2 = "execution"
 *      输出: 5
 *      解释:
 *          intention -> inention (删除 't')
 *          inention -> enention (将 'i' 替换为 'e')
 *          enention -> exention (将 'n' 替换为 'x')
 *          exention -> exection (将 'n' 替换为 'c')
 *          exection -> execution (插入 'u')
 *
 */
public class EditDistance {

    /**
     * 解法1：动态规划
     *
     * 状态：
     *      dp[i][j] 表示 word1 的前 i 个字母和 word2 的前 j 个字母之间的编辑距离。
     *
     * 状态转移方程：
     *      如果 word1[i] = word2[j]：  dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1] - 1);
     *      如果 word1[i] != word2[j]：  dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
     *
     * 边界状态：
     *      一个空串和一个非空串的编辑距离为 dp[i][0] = i 和 dp[0][j] = j。
     *
     * 复杂度分析
     *      时间复杂度 ：O(mn)，两层循环显而易见。
     *      空间复杂度 ：O(mn)，循环的每一步都要记录结果。
     *
     * 参考：
     * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode/
     * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-mian-shi-ti-xiang-jie-by-labuladong/
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m == 0 || n == 0) return m + n;

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                int leftUp = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    dp[i][j] = 1 + Math.min(Math.min(left, up), leftUp);
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(left, up), leftUp - 1);
                }
            }
        }
        return dp[m][n];
    }
}
