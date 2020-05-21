package h97_InterleavingString;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *      输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *      输出: true
 *
 * 示例 2:
 *      输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *      输出: false
 *
 */

public class InterleavingString {

    /**
     * 解法1：DP（二维DP）
     *
     * 首先我们考虑在每个字符串的最前面加一个空字符（为了下标便于表示）
     *
     * dp[i][j] 表示用 s1 的前 i 个字符和 s2 的前 j 个字符，能否交错构成 s3 的前 (i+j) 个字符，考虑以下两种情况：
     *      1、s1 的第 i 个字符和 s2 的第 j 的字符都不能匹配 s3 的第 (i+j) 个字符，所以s1 的前 i 个字符和 s2 的前 j 个
     *      字符不能交错构成 s3 的前 (i+j) 个字符，即 dp[i][j] = false
     *
     *      2、s1 的第 i 个字符或者 s2 的第 j 的字符能匹配 s3 的第 (i+j) 个字符。
     *      如果是s1 的第 i 个字符与s3 的第 (i+j) 个字符匹配，那么就必须确保s1 的前 (i-1) 个字符和 s2 的前 j 个字符能
     *      交错构成 s3 的前 (i+j-1) 个字符。即：
     *      s1[i-1] == s3[i+j-1] && dp[i-1][j] 为true
     *      类似的，如果是s2 的第 j 个字符与s3 的第 (i+j) 个字符匹配，那么就必须确保s1 的前 i 个字符和 s2 的前 (j-1) 个
     *      字符能交错构成 s3 的前 (i+j-1) 个字符。即：
     *      s2[j-1] == s3[i+j-1] && dp[i][j-1] 为true
     *
     *      注意：上面dp数组和字符串中的索引不是同一个意思
     *
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 != len3 - len2) return false;
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[i][j - 1] && s2.charAt(j-1) == s3.charAt(i+j-1);
            }
        }
        return dp[len1][len2];
    }

    /**
     * 解法2：DP（一维DP）
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 != len3 - len2) return false;
        boolean[] dp = new boolean[len2 + 1];

        dp[0] = true;
        for (int j = 1; j <= len2; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= len1; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("a", "", "a"));
    }
}
