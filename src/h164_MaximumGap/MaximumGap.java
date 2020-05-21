package h164_MaximumGap;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 *
 * Example 1:
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 *              (3,6) or (6,9) has the maximum difference 3.
 *
 * Example 2:
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 * Note:
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 *
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 说明:
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */

public class MaximumGap {

    /**
     * 方法1：桶和鸽笼原理
     *
     * 时间复杂度：O(n+b)≈O(n)
     * n为数组元素个数，b为桶的个数
     * 线性遍历一遍数组中的元素，复杂度为 O(n)。找到桶之间的最大间距需要线性遍历一遍所有的桶，复杂度为O(b)。所以总复杂度是线性的。
     *
     * 空间复杂度：O(2*b)≈O(b) 的额外空间。
     * 每个桶只需要存储最大和最小元素，因此额外空间和桶个数线性相关。
     *
     * https://www.cnblogs.com/bywallance/p/5761269.html
     */
    public int maximumGap(int[] nums) {
        int len;
        if (null == nums || (len = nums.length) < 2) return 0;

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        // 求解数组最值
        for (int i = 0; i < len; i++) {
           if (nums[i] > maxValue) maxValue = nums[i];
           if (nums[i] < minValue) minValue = nums[i];
        }

        // 数组内的元素值相同
        if (maxValue == minValue) return 0;
        // 数组仅有两个元素
        if (len == 2) return maxValue - minValue;

        int averageGap = (int)Math.ceil((double)(maxValue - minValue) / (len - 1)); // 求解桶间差值,向上取整（桶的大小），len个元素，(len-1)个间隙
        int n = (maxValue - minValue) / averageGap; // 桶的个数
        // 因此第i个桶保存的值区间为：[min + (i-1) * averageGap, min + i*averageGap )（下标从 1 开始）。

        int[] maxBucket = new int[n + 1];
        int[] minBucket = new int[n + 1];
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        Arrays.fill(minBucket, Integer.MAX_VALUE);

        // 桶映射
        int index;
        for (int val : nums) {
            index = (val - minValue) / averageGap;
            maxBucket[index] = Math.max(maxBucket[index], val);
            minBucket[index] = Math.min(minBucket[index], val);
        }

        // 求解最大gap,最大差值位于后桶的min - 前桶的max
        int gap = 0;
        int pre = maxBucket[0]; // 这个值是一定存在的，不会是初始值Integer.MIN_VALUE
        for (int i = 1; i <= n; i++) {
            if (minBucket[i] == Integer.MAX_VALUE && maxBucket[i] == Integer.MIN_VALUE) continue; // 忽略空桶
            gap = Math.max(gap, minBucket[i] - pre);
            pre = maxBucket[i];
        }
        return gap;
    }
}
