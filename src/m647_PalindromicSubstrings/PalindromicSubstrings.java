package m647_PalindromicSubstrings;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *      输入: "abc"
 *      输出: 3
 *      解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 *      输入: "aaa"
 *      输出: 6
 *      说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 *      输入的字符串长度不会超过1000。
 *
 */

public class PalindromicSubstrings {

    /**
     * 方法1：中心扩展法
     * 从字符串的某一位开始，尝试着去扩展子字符串。
     */
    private int cnt = 0;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            extendSubstring(s, i, i);
            extendSubstring(s, i, i + 1);
        }
        return cnt;
    }

    private void extendSubstring(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            cnt++;
        }
    }
}
