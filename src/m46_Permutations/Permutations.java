package m46_Permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 题目链接：https://leetcode-cn.com/problems/permutations/
 */

public class Permutations {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 第一种实现
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> track = new ArrayList<>();
        backtrack(nums, track);
        return res;
    }

    /**
     * 回溯
     *
     * @param nums 选择列表
     * @param track 路径
     */
    private void backtrack(int[] nums, List<Integer> track) {
        // 结束条件
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        // 数组中每个元素都可以作为第一个元素
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) continue; // 这里没有显式记录选择列表，而是通过 nums 和 track 推导出当前的选择列表
            track.add(nums[i]); // 做选择
            backtrack(nums, track);
            track.remove(track.size() - 1); // 撤销选择
        }
    }

    /**
     * 第二种实现
     */
    public List<List<Integer>> permute02(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        boolean[] visited = new boolean[nums.length];
        backtrack(new ArrayList<>(), visited, nums);
        return res;
    }

    private void backtrack(List<Integer> track, boolean[] visited, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            track.add(nums[i]);
            visited[i] = true;
            backtrack(track, visited, nums);
            track.remove(track.size() - 1);
            visited[i] = false;
        }
    }
}
