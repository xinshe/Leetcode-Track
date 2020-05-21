package m377_CombinationSumIV;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 *
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 *
 * 示例:
 *      nums = [1, 2, 3]
 *      target = 4
 *
 *      所有可能的组合为：
 *      (1, 1, 1, 1)
 *      (1, 1, 2)
 *      (1, 2, 1)
 *      (1, 3)
 *      (2, 1, 1)
 *      (2, 2)
 *      (3, 1)
 *
 *      请注意，顺序不同的序列被视作不同的组合。
 *      因此输出为 7。
 *
 * 进阶：
 *      如果给定的数组中含有负数会怎么样？
 *      问题会产生什么变化？
 *      我们需要在题目中添加什么限制来允许负数的出现？
 */
public class CombinationSumIV {

    /**
     * 解法1：DP
     *
     * 按列填充（下面数据可能不对）
     *              0   1   2   3   4   (target)
     * null         1   0   0   0   0
     * 1            1   1   1   1   1
     * 2            1   1   2   3   5
     * 3            1   1   2   4   7
     * (数组中的数)
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {     // target
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {     // 目标
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
