package m300_LongestIncreasingSubsequence;

import java.util.Arrays;
import java.util.Stack;

/**
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *      输入: [10,9,2,5,3,7,101,18]
 *      输出: 4
 *      解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 *      可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *      你算法的时间复杂度应该为O(n^2) 。
 *      进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 注意「子序列」和「子串」这两个名词的区别，子串一定是连续的，而子序列不一定是连续的。
 *
 */

public class LongestIncreasingSubsequence {

    /**
     * 方法1：DP
     *
     * 设置动态规划数组dp[]，第i个状态 dp[i] 代表以第 i 个元素结尾的最长上升子序列的长度。
     *
     * 时间复杂度：O(n^2)，有两个 n 的循环。
     * 空间复杂度：O(n)，用了大小为 n 的矩阵 dp。
     *
     * 参考：
     * https://mp.weixin.qq.com/s/EWLi6sP3l4xsuc6GDpQAjw
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > max) max = dp[i];
        }
        return max;
    }

    /**
     * 解法2：二分查找
     *
     * 最长递增子序列和一种叫做 patience game 的纸牌游戏有关，甚至有一种排序方法就叫做 patience sorting（耐心排序）。
     *
     * 首先，给你一排扑克牌，我们像遍历数组那样从左到右一张一张处理这些扑克牌，最终要把这些牌分成若干堆。
     * 处理这些扑克牌要遵循以下规则：
     *      只能把点数小的牌压到点数比它大的牌上。如果当前牌点数较大没有可以放置的堆，则新建一个堆，把这张牌放进去。
     *      如果当前牌有多个堆可供选择，则选择最左边的堆放置。
     *
     * 时间复杂度：O(N*logN)
     * 空间复杂度：O(N)
     */
    public int lengthOfLIS02(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int len = nums.length;
        int[] top = new int[len];

        int piles = 0;
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            int left = 0;
            int right = piles;
            // 找到第一个堆顶元素比cur大的堆，然后将cur插入进去
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (cur > top[mid]) {
                    left = mid + 1; // mid对应的这个堆，肯定不能插入进去，所以mid要加1
                } else if (cur <= top[mid]) {
                    right = mid; // mid对应的这个堆，还有可能成为要插入的堆，所以mid直接赋给right
                }
            }
            // 没有找到合适的牌堆，新建一堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = cur;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

    /**
     * 解法3：借助于Stack
     *
     */
    public int lengthOfLIS03(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 1; i < len; i++) {
            if (nums[i] > stack.peek()) {
                stack.push(nums[i]);
            } else {
                for (int j = 0; j < stack.size(); j++) {    // 在这里用二分查找找到插入点，时间复杂度可以降到 O(N*logN)
                    if (stack.get(j) >= nums[i]) {
                        stack.set(j, nums[i]);
                        break;
                    }
                }
            }
        }
        return stack.size();
    }

}
