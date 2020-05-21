package h87_ScrambleString;

import java.util.HashMap;

/**
 * 87. 扰乱字符串
 *
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 *
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 *
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 *
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 *
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 *
 * 示例1:
 *      输入: s1 = "great", s2 = "rgeat"
 *      输出: true
 *
 * 示例2:
 *      输入: s1 = "abcde", s2 = "caebd"
 *      输出: false
 *
 */
public class ScrambleString {

    /**
     * 这里可以简单地理解为：给定一个字符串s1和一个将s1中字符打乱的字符串s2，判断s2是不是s1的扰乱字符串。
     * 所以，字符串s1和s2一定长度相等，并且各个字符出现的次数相同。
     * 另外，如果s1等于s2，那么s2肯定是s1的扰乱字符串。
     */

    /**
     * 解法1：递归（未使用memoization技术）
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        int[] letters = new int[26];    // 其实不一定都是小写字母，所以可用Map来实现
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i)-'a']++;
            letters[s2.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    // 解法1的另一种实现，中间letters用HashMap实现的
    public static boolean isScramble2(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        HashMap<Character, Integer> letters = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            int count1 = letters.getOrDefault(c1, 0) + 1;
            letters.put(c1, count1);
            int count2 = letters.getOrDefault(c2, 0) - 1;
            letters.put(c2, count2);
        }

        for (Character ch : letters.keySet()) {
            if (letters.get(ch) != 0) return false;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble2(s1.substring(0, i), s2.substring(0, i))
                    && isScramble2(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble2(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble2(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解法2：递归（使用memoization技术）
     */

    /**
     * 解法3：DP（三维DP）
     */

    public static void main(String[] args) {
        System.out.println(isScramble2("great", "rgeat"));
    }
}
