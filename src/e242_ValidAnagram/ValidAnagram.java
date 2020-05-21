package e242_ValidAnagram;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ （HashMap解法）
 *
 */

public class ValidAnagram {

    /**
     * 方法1 —— 我自己的实现
     *
     * 可以用 HashMap 来映射字符与出现次数，然后比较两个字符串出现的字符数量是否相同。
     *
     * 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组对字符串出现的字符进行统计，
     * 不再使用 HashMap。
     *
     * 复杂度分析
     *      时间复杂度：O(n)。
     *      空间复杂度：O(1)。尽管我们使用了额外的空间，但是空间的复杂性是 O(1)，因为无论 N 有多大，表的大小都保持不变。
     *
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        int[] arrS = new int[26];
        int[] arrT = new int[26];
        int lenS = s.length();
        int lenT = t.length();
        for (int i = 0; i < lenS; i++) {
            arrS[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < lenT; i++) {
            arrT[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arrS[i] != arrT[i]) return false;
        }
        return true;
    }

    /**
     * 方法1 —— 别人的实现（推荐）
     */
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) return false;
        }
        return true;
    }

}
