package m64_MinimumPathSum;

/**
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * 题目链接：https://leetcode-cn.com/problems/minimum-path-sum/
 *
 */
public class MinimumPathSum {

    /**
     * 解法1：二维DP
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     */
    public static int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length; // m行
        int n = grid[0].length; // n列
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 1 && j >= 1) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                } else if (i >= 1) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else if (j >= 1) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else {
                    dp[i][j] = grid[i][j]; // 等价于 dp[0][0] = grid[0][0]
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 解法2：一维DP（自顶向下）
     *
     * 自底向上也可以！
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(n)
     *
     */
    public static int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length; // m行
        int n = grid[0].length; // n列
        int[] dp = new int[n];
        // 第一行
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i]; // 这里用第一行初始化时，记得累加前面一个元素的值
        }
        // 剩下的行
        for (int i = 1; i < m; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }

    /**
     * 解法3：可以直接在grid数组上操作，思路和解法1一样
     */
    public static int minPathSum3(int[][] grid) {
        int m, n;
        if (grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0) {
            return 0;
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }



}
