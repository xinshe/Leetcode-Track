package m78_Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *    [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 题目链接：https://leetcode-cn.com/problems/subsets/
 */

public class Subsets {

    /**
     * 解法1：回溯法
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> track = new ArrayList<>();
        backtrack(0, nums, track);
        return res;
    }

    private void backtrack(int start, int[] nums, List<Integer> track) {
        res.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(i + 1, nums, track);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> r = new Subsets().subsets(nums);
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).toString());
        }
    }
}
