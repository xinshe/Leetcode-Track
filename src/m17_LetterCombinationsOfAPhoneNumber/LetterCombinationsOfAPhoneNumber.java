package m17_LetterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 */
public class LetterCombinationsOfAPhoneNumber {

    private final String[] KEYS = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(new StringBuilder(), digits, res);
        return res;
    }

    /**
     * 路径、选择列表、结束条件
     */
    private void backtrack(StringBuilder track, String digits, List<String> res) {
        // 结束条件
        if (track.length() == digits.length()) {
            res.add(track.toString());
            return;
        }
        // 选择列表
        int curDigit = digits.charAt(track.length()) - '0';
        String letters = KEYS[curDigit];
        for (char c : letters.toCharArray()) {
            track.append(c);
            backtrack(track, digits, res);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
