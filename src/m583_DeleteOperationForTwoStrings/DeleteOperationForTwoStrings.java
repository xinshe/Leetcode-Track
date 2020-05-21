package m583_DeleteOperationForTwoStrings;

/**
 * 583. 两个字符串的删除操作
 *
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 示例1:
 *      输入: "sea", "eat"
 *      输出: 2
 *      解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *
 * 说明:
 *      给定单词的长度不超过500。
 *      给定单词中的字符只含有小写字母。
 */

public class DeleteOperationForTwoStrings {

    /**
     * 解法1：DP（直接运用DP）
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0 || len2 == 0) return Math.max(len1, len2);
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) { // 处理第一列
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            char c1 = word1.charAt(i-1);
            for (int j = 1; j <= len2; j++) {
                char c2 = word2.charAt(j-1);
                if (c1 == c2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 解法2：DP（运用最长公共子序列来做）
     *
     * 先求出两个字符串的最长公共子序列 n，然后求 len(word1) + len(word2) - 2 * n
     */
    public int minDistance2(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0 || len2 == 0) return Math.max(len1, len2);

        // 求word1和word2的最长公共子序列长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return len1 + len2 - 2 * dp[len1][len2];
    }

    /**
     * 解法3：一维DP（把解法1的二维DP改成一维DP）
     *
     * 复杂度分析：
     *      时间复杂度：O(m*n)。我们需要求解大小为 n 的 dp 数组 m 次。m 和 n 分别是字符串 s1 和 s2 的长度。
     *      空间复杂度：O(n)。使用了大小为 n 的 dp 数组。
     *
     */
    public int minDistance3(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0 || len2 == 0) return Math.max(len1, len2);
        int[] dp = new int[len2 + 1]; // 保存每次的上一行的结果
        for (int i = 0; i <= len1; i++) {
            int[] tmp = new int[len2 + 1];  // 保存当前行的结果
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    tmp[j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    tmp[j] = dp[j - 1];
                } else {
                    tmp[j] = Math.min(dp[j], tmp[j - 1]) + 1;
                }
            }
            dp = tmp;
        }
        return dp[len2];
    }

}
