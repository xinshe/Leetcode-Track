package m15_ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有
 * 满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 题目链接：https://leetcode-cn.com/problems/3sum
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int a = 0; a < nums.length; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int c = nums.length - 1;  // c一定要在第二个循环开始前初始化，不然会超时
            for (int b = a + 1; b < nums.length; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                while (b < c && nums[b] + nums[c] > -nums[a]) {
                    c--;
                }
                if (b < c && nums[a] + nums[b] + nums[c] == 0) {
                    List<Integer> t = new ArrayList<>();
                    t.add(nums[a]);
                    t.add(nums[b]);
                    t.add(nums[c]);
                    res.add(t);
                }
            }
        }
        return res;
    }
}
