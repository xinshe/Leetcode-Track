package m279_PerfectSquares;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的
 * 个数最少。
 *
 * 示例1:
 *      输入: n = 12
 *      输出: 3
 *      解释: 12 = 4 + 4 + 4.
 *
 * 示例2:
 *      输入: n = 13
 *      输出: 2
 *      解释: 13 = 4 + 9.
 *
 */

public class PerfectSquares {

    /**
     * 解法1：DP
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        return dp[n];
    }

    /**
     * 解法2：BFS
     */
    public int numSquares2(int n) {
        List<Integer> squares = generateSquares(n);

        boolean[] marked = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        marked[n] = true;

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            while (size-- > 0) {
                int cur = queue.poll();
                for (int square : squares) {
                    int next = cur - square;
                    if (next < 0) break;
                    if (next == 0) return level;
                    if (marked[next]) continue;

                    queue.add(next);
                    marked[next] = true;
                }
            }
        }
        return n;
    }

    // 生成小于或等于n的所有平方数
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }

    public static void main(String[] args) {
        System.out.print(numSquares(13));
    }
}
