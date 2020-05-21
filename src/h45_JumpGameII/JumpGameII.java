package h45_JumpGameII;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * 给定一个非负整数数组，数组元素表示能跳跃的最大步数，比如，如果数组元素为3，就表示在当前位置向后可以跳跃1步、
 * 2步或者3步，题目求的是从第一个元素所在的位置跳跃到最后一个元素所在的位置所需的 最少跳跃次数
 *
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * https://www.bilibili.com/video/av55631531?from=search&seid=17735430806819356798
 */

public class JumpGameII {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 利用最远可达距离来做。用curEnd来表示当前范围内的最远能到达的地方，然后在这个范围内进行遍历，如果当前范围内
     * 存在新的最远能到达的元素，即i + nums[i] > futherPos，就更新futherPos，当遍历到curEnd位置处，就表示可以进行
     * 下一次跳跃了，steps++，并更新curEnd
     */
    public int jump(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int steps = 0;
        int curEnd = 0;
        int futherPos = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > futherPos) futherPos = i + nums[i];
            if (i == curEnd) {
                steps++;
                curEnd = futherPos;
            }
        }
        return steps;
    }
}
