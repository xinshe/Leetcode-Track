package m47_PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 题目链接：https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermutationsII {

    // 参考：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
    // 在 LeetCode上提交时，去掉静态信息
    static List<List<Integer>> res = new ArrayList<>();
    static boolean[] used;

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> track = new ArrayList<>();
        used = new boolean[nums.length];
        // 排序（升序或者降序都可以），为了剪枝方便
        Arrays.sort(nums);
        backtrack(nums, track);
        return res;
    }

    private static void backtrack(int[] nums, List<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i != 0 && !used[i - 1] && nums[i] == nums[i - 1]) continue; // 这里used[i - 1]加不加!都能通过
            if (used[i]) continue;

            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, track);
            used[i] = false;
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        List<List<Integer>> res = permuteUnique(nums);
        for (List<Integer> item : res) {
            System.out.println(item.toString());
        }
    }
}
