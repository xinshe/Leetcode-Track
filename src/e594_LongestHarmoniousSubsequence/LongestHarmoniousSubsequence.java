package e594_LongestHarmoniousSubsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 * 说明: 输入的数组长度最大不超过20,000.
 *
 * 注意：序列的元素不一定是数组的连续元素。
 */

public class LongestHarmoniousSubsequence {

    /**
     * 方法1：HashMap解法 + 两次扫描
     *
     * 复杂度分析：
     *      时间复杂度：O(N)，其中 N 是数组的长度。
     *      空间复杂度：O(N)，用来存储哈希映射。
     */
    public int findLHS(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countForNum.keySet()) {
            if (countForNum.containsKey(num + 1)) {
                longest = Math.max(longest, countForNum.get(num) + countForNum.get(num + 1));
            }
        }
        return longest;
    }

    /**
     * 方法2：Hashmap解法 + 单次扫描
     *
     * 复杂度分析：
     *      时间复杂度：O(N)，其中 N 是数组的长度。
     *      空间复杂度：O(N)，用来存储哈希映射。
     */
    public int findLHS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> countForNum = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
            int cur = countForNum.get(num);
            if (countForNum.containsKey(num + 1)) {
                res = Math.max(res, cur + countForNum.get(num + 1));
            }
            if (countForNum.containsKey(num - 1)) {
                res = Math.max(res, cur + countForNum.get(num - 1));
            }
        }
        return res;
    }
}
