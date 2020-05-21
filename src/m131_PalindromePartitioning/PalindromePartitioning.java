package m131_PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        backtrack(new ArrayList<>(), s, res);
        return res;
    }

    private void backtrack(List<String> track, String s, List<List<String>> res) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s, 0 , i)) {
                track.add(s.substring(0, i + 1));
                backtrack(track, s.substring(i + 1), res);
                track.remove(track.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int begin, int end) {
        while (begin < end) {
            if (s.charAt(begin++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
