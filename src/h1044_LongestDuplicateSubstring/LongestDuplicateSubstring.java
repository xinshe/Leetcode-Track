package h1044_LongestDuplicateSubstring;

/**
 * 1044. 最长重复子串
 *
 * 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
 * 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 ""。）
 *
 * 示例 1：
 * 输入："banana"
 * 输出："ana"
 *
 * 示例 2：
 * 输入："abcd"
 * 输出：""
 *
 * 提示：
 * 2 <= S.length <= 10^5
 * S 由小写英文字母组成。
 *
 * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring
 */
public class LongestDuplicateSubstring {

    /**
     * 解法1：DP (没通过)
     *
     * 参考：https://blog.csdn.net/qq_17550379/article/details/89441080?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     */
    public String longestDupSubstring(String S) {
        int len = 0;
        if (S == null || (len = S.length()) <= 1) {
            return "";
        }

        int[][] dp = new int[len + 1][len + 1];

        int maxLen = 0;
        int low = -1, high = -1;
        for (int i = 1; i <= len; i++) {
            char a = S.charAt(i - 1);
            for (int j = i + 1; j <= len; j++) {
                char b = S.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        low = i;
                        high = j;
                    }
                }
            }
        }
        return low == -1 ? "" : S.substring(low - 1, high);
    }

    /**
     * 其他解法
     * https://blog.csdn.net/qq_34826261/article/details/97319790
      */
}
