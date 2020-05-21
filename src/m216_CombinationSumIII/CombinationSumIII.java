package m216_CombinationSumIII;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *      所有数字都是正整数。
 *      解集不能包含重复的组合。
 *
 * 示例1:
 *      输入: k = 3, n = 7
 *      输出: [[1,2,4]]
 *
 * 示例2:
 *      输入: k = 3, n = 9
 *      输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n > 45) return res;
        backtrack(new ArrayList<>(), 1, n, k, res);
        return res;
    }

    private void backtrack(List<Integer> track, int start, int n, int k, List<List<Integer>> res) {
        if (track.size() >= k || n <= 0) {
            if (track.size() == k && n == 0) {
                res.add(new ArrayList<>(track));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            track.add(i);
            backtrack(track, i + 1, n - i, k, res);
            track.remove(track.size() - 1);
        }
    }

}
