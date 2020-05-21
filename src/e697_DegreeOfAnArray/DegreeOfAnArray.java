package e697_DegreeOfAnArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 *      输入: [1, 2, 2, 3, 1]
 *      输出: 2
 *      解释:
 *      输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 *      连续子数组里面拥有相同度的有如下所示:
 *      [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 *      最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 * 示例 2:
 *      输入: [1,2,2,3,1,4,2]
 *      输出: 6
 *
 * 注意:
 *      nums.length 在1到50,000区间范围内。
 *      nums[i] 是一个在0到49,999范围内的整数。
 */

public class DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> numCnts = new HashMap<>();
        Map<Integer, Integer> firstIndexes = new HashMap<>();
        Map<Integer, Integer> lastIndexes = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            numCnts.put(nums[i], numCnts.getOrDefault(nums[i], 0) + 1);
            lastIndexes.put(nums[i], i);
            if (!firstIndexes.containsKey(nums[i])) {
                firstIndexes.put(nums[i], i);
            }
        }
        int maxCnt = 0;
        for (int num : nums) {
            maxCnt = Math.max(maxCnt, numCnts.get(num));
        }
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            if (numCnts.get(num) == maxCnt) {
                res = Math.min(res, lastIndexes.get(num) - firstIndexes.get(num));
            }
        }
        return res + 1;
    }
}
