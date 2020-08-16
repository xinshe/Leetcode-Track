package m5_LongestPalindromicSubstring;

/**
 * 5. ������Ӵ�
 *
 * ����һ���ַ��� s���ҵ� s ����Ļ����Ӵ�������Լ���?s ����󳤶�Ϊ 1000��
 *
 * ʾ�� 1��
 * ����: "babad"
 * ���: "bab"
 * ע��: "aba" Ҳ��һ����Ч�𰸡�
 *
 * ʾ�� 2��
 * ����: "cbbd"
 * ���: "bb"
 *
 * ��Ŀ���ӣ�https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindromicSubstring {

    /**
     * �ⷨ1��
     * DP��Dynamic Programming���ⷨ
     * dp[i][j] ��ʾ��i���ַ�����j���ַ����Ӵ��Ƿ��ǻ����Ӵ�
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
     * �ⷨ2��������չ�㷨
     */
    public String longestPalindrome02(String s) {
        int n;
        if (s == null || (n = s.length()) <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        for(int i = 0; i < n; i++) {
            int[] ret1 = expandAroundCenter(s, i, i); // ����
            if(ret1[2] > end - start + 1) {
                start = ret1[0];
                end = ret1[1];
            }

            int[] ret2 = expandAroundCenter(s, i, i+1); // ˫��
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
        return new int[]{L + 1, R - 1, R - L - 1};	// ע�⣺(R-1)-(L+1)+1
    }

}
