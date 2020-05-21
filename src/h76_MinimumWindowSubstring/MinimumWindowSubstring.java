package h76_MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 *
 * 注意：子串应该要包含t中所有元素，如果t中有2个A，那么子串中也应该要包含2个A
 */
public class MinimumWindowSubstring {

    /**
     * 滑动窗口
     *
     * 复杂度分析
     * 时间复杂度: O(|S| + |T|)，其中 ∣S∣ 和 ∣T∣ 代表字符串 S 和 T的长度。在最坏的情况下，
     * 可能会对S 中的每个元素遍历两遍，左指针和右指针各一遍。
     * 空间复杂度: O(|S| + |T|)。当窗口大小等于∣S∣时为 S。当 ∣T∣ 包括全部唯一字符时为 T 。
     *
     */
    public String minWindow(String s, String t) {
        int sLen, tLen;
        if (s == null || (sLen = s.length()) == 0 || t == null || (tLen = t.length()) == 0 || tLen > sLen) {
            return "";
        }

        Map<Character, Integer> dicT = new HashMap<>();
        Map<Character, Integer> windowCounts = new HashMap<>();

        for (int i = 0; i < tLen; i++) {
            int count = dicT.getOrDefault(t.charAt(i), 0);
            dicT.put(t.charAt(i), count + 1);
        }

        int[] res = {-1, 0, 0};
        int left = 0;
        int right = 0;
        int formed = 0;
        int required  = dicT.size();
        while (right < sLen) {
            // 移动right
            char ch = s.charAt(right);
            int count = windowCounts.getOrDefault(ch, 0);
            windowCounts.put(ch, count + 1);
            if (dicT.containsKey(ch) && dicT.get(ch).intValue() == windowCounts.get(ch).intValue()) { // 注意intValue()
                formed++;
            }

            // 移动left
            while (left <= right && formed == required) {
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                ch = s.charAt(left);
                windowCounts.put(ch, windowCounts.get(ch) - 1);
                if (dicT.containsKey(ch) && windowCounts.get(ch) < dicT.get(ch)) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    /**
     * 优化的滑动窗口
     * 当s中有大量t中没有的元素时，可以建立一个 filtered_S列表，其中包括 S 中的全部字符以及它们在S的下标，
     * 但这些字符必须在 T中出现。
     *
     * S = "ABCDDDDDDEEAFFBC" T = "ABC"
     * filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
     *
     */
}
