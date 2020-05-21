package m91_DecodeWays;

/**
 * 91. 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例1:
 *      输入: "12"
 *      输出: 2
 *      解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例2:
 *      输入: "226"
 *      输出: 3
 *      解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 */

public class DecodeWays {

    /**
     * 解法1：DP
     *
     * 状态：dp[i] 表示字符串前i个字符的解码方法总数
     *
     * 状态转移方程：
     *      dp[i] = dp[i - 1] + dp[i - 2]
     *      但是这里需要分情况讨论：
     *      1、当前字符（即第 i 个字符）表示的数在[1,9]之间，那么dp[i]要加上dp[i - 1]
     *      2、当前字符（即第 i 个字符）和当前字符前面那个字符（即第 i - 1 个字符）构成的两位数在[1,26]之间，那么dp[i]要加上dp[i - 2]
     *
     * 状态边界条件：
     *      dp[0] = 1
     *
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // 这里要设为1，不懂？
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) { // dp数组索引 i 对应字符串索引 i-1
            int one = Integer.valueOf(s.substring(i - 1, i)); // 当前位置对应的数（这里要注意数组索引和字符串索引的对应关系）
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i -2) == '1' || s.charAt(i -2) == '2') { // 当前位置前面一个数
                int two = Integer.valueOf(s.substring(i - 2, i)); // 当前位置对应的数和当前位置前面一个数构成的两位数
                if (1 <= two && two <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}
