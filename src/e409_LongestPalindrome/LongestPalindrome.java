package e409_LongestPalindrome;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 */

public class LongestPalindrome {

    /**
     * 方法1 —— 自己的实现
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] cnts = new int[128];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int maxLen = 0;
        boolean flag = false;
        for (int cnt : cnts) {
            if (cnt % 2 == 0) maxLen += cnt;
            else if (--cnt >= 0) {
                maxLen += cnt;
                flag = true;
            }
        }
        return flag ? maxLen + 1 : maxLen;
    }

    /**
     * 方法1 —— 别人的实现
     */
    public int longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] cnts = new int[128];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int res = 0;
        for (int cnt : cnts) {
            res += (cnt / 2) * 2;
        }
        if (res < s.length()) res++;
        return res;
    }

    public static void main(String[] args) {
        char c = 'A'; // 65
        char c1 = 'a'; // 97
        char c2 = 'z'; // 122
        System.out.println((int)c);
        System.out.println((int)c1);
        System.out.println((int)c2);
    }
}
