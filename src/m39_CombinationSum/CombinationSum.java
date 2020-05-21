package m39_CombinationSum;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *      所有数字（包括 target）都是正整数。
 *      解集不能包含重复的组合。 
 *
 * 示例1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target == 0) return res;
        List<Integer> track = new ArrayList<>();
        backtrack(0, candidates, target, track);
        return res;
    }

    private static void backtrack(int start, int[] candidates, int target, List<Integer> track) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            track.add(candidates[i]);
            backtrack(i, candidates, target - candidates[i], track); // 这里一定要注意：实参一定是i不是start
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,6,7};
        combinationSum(nums,7);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
    }
}
