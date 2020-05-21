package m120_Triangle;

import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *      如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */

public class Triangle {

    /**
     * 解法1：DP（自底向上）
     *
     * 空间复杂度：O(N^2)
     *
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int m;
        if (triangle == null || (m =  triangle.size()) == 0) return 0;
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            dp[m - 1][i] = triangle.get(m - 1).get(i);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * 解法2：DP（自底向上）
     *
     * 空间复杂度：O(N)
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int m = triangle.size();
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = triangle.get(m - 1).get(i); // 从三角形最后一行开始
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
