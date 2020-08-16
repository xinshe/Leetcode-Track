package m5_LongestPalindromicSubstring;

/**
 * 5. 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设?s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 题目链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindromicSubstring {

    /**
     * 解法1：
     * DP（Dynamic Programming）解法
     * dp[i][j] 表示第i个字符到第j个字符的子串是否是回文子串
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        char[] ch = s.toCharArray();
        int start = 0;
        int end = 0;

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(j - i < 2) // j == i or j - 1 == i
                    dp[i][j] = (ch[i] == ch[j]);
                else
                    dp[i][j] = (ch[i] == ch[j] && dp[i + 1][j - 1]);

                if(dp[i][j] && end - start <= j - i) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 解法2：中心扩展算法
     */
    public String longestPalindrome02(String s) {
        int n;
        if (s == null || (n = s.length()) <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        for(int i = 0; i < n; i++) {
            int[] ret1 = expandAroundCenter(s, i, i); // 单核
            if(ret1[2] > end - start + 1) {
                start = ret1[0];
                end = ret1[1];
            }

            int[] ret2 = expandAroundCenter(s, i, i+1); // 双核
            if (ret2[2] > end - start + 1) {
                start = ret2[0];
                end = ret2[1];
            }
        }

        return s.substring(start, end + 1);
    }

    private int[] expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return new int[]{L + 1, R - 1, R - L - 1};	// 注意：(R-1)-(L+1)+1
    }

}
