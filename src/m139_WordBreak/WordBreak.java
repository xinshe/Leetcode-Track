package m139_WordBreak;

import java.util.List;

/**
 * 139. 单词拆分
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *      拆分时可以重复使用字典中的单词。
 *      你可以假设字典中没有重复的单词。
 *
 * 示例1：
 *      输入: s = "leetcode", wordDict = ["leet", "code"]
 *      输出: true
 *      解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例2：
 *      输入: s = "applepenapple", wordDict = ["apple", "pen"]
 *      输出: true
 *      解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *          注意你可以重复使用字典中的单词。
 *
 * 示例3：
 *      输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 *      输出: false
 *
 */
public class WordBreak {

    /**
     * 解法1：DP（自己的实现）
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) return false;
        if (wordDict.contains(s)) return true;

        int len = s.length();
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (wordDict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            } else {
                for (int j = 0; j < i; j++) {
                    dp[i] = dp[j] && wordDict.contains(s.substring(j + 1, i + 1)); // 在j和j+1之间切割
                    if (dp[i]) break;
                }
            }
        }
        return dp[len - 1];
    }

    /**
     * 解法2：DP
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || wordDict == null) return false;
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (String word : wordDict) {
                int wordLen = word.length();
                if (i >= wordLen && word.equals(s.substring(i - wordLen, i))) {
                    dp[i] = dp[i] || dp[i - wordLen];
                }
            }
        }
        return dp[len];
    }
}
