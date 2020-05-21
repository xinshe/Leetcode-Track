package m343_IntegerBreak;

/**
 * 343. 整数拆分
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例1:
 *      输入: 2
 *      输出: 1
 *      解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例2:
 *      输入: 10
 *      输出: 36
 *      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *      说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak {

    /**
     * 方法1：DP
     * 状态转移方程
     * dp[i] = max(dp[i], max(j, dp[j]) * (i-j))
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * (i-j));
            }
        }
        return dp[n];
    }

    /**
     * 方法2：
     * 当n=2时，只能划分为1*1；
     * 当n=3时，只能划分为2*1；
     * 当n=4时，可以划分为2*2（或者不划分也是一样的）；
     * 当n>=5时，3*(n-3) >= 2*(n-2) > n，所以划分为3*(n-3)，再对(n-3)继续进行划分。
     */
    public static int integerBreak02(int n) {
        if (n < 4) return n-1; // n = 2, 3
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res *= n;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(integerBreak02(10));
        System.out.println(integerBreak02(2));
    }
}
