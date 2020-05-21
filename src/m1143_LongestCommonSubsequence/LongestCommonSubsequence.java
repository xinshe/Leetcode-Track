package m1143_LongestCommonSubsequence;

/**
 * 1143. 最长公共子序列
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除
 * 任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有
 * 的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 示例1:
 *      输入：text1 = "abcde", text2 = "ace"
 *      输出：3
 *      解释：最长公共子序列是 "ace"，它的长度为 3。
 *
 * 示例2:
 *      输入：text1 = "abc", text2 = "abc"
 *      输出：3
 *      解释：最长公共子序列是 "abc"，它的长度为 3。
 *
 * 示例3:
 *      输入：text1 = "abc", text2 = "def"
 *      输出：0
 *      解释：两个字符串没有公共子序列，返回 0。
 *  
 *
 * 提示:
 *      1 <= text1.length <= 1000
 *      1 <= text2.length <= 1000
 *      输入的字符串只含有小写英文字符
 *
 */
public class LongestCommonSubsequence {

    /**
     * 解法1：DP
     *
     * dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度。考虑 S1i 与 S2j 值是否相等，分为两种情况：
     *      1、当 S1[i] == S2[j] 时，那么就能在 S1 的前 i-1 个字符与 S2 的前 j-1 个字符最长公共子序列的基础上再加上
     *      S1[i] 这个值，最长公共子序列长度加 1，即 dp[i][j] = dp[i-1][j-1] + 1。
     *      2、当 S1[i] != S2[j] 时，此时最长公共子序列为 S1 的前 i-1 个字符和 S2 的前 j 个字符最长公共子序列，
     *      或者 S1 的前 i 个字符和 S2 的前 j-1 个字符最长公共子序列，取它们的最大者，即 dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int len1 = text1.length();
        int len2 = text2.length();
        if (len1 == 0 || len2 == 0) return 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

}
