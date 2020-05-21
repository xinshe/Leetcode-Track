package m22_GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * 22.括号生成
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class GenerateParentheses {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack(n, n, new String());
        return res;
    }

    private void backtrack(int left, int right, String str) {
        if (left > right) return;
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > 0) {
            backtrack(left - 1, right, str + "(");
        }
        if (right > 0) {
            backtrack(left, right - 1, str + ")");
        }
    }

}
