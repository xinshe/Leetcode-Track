package m55_JumpGame;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 给定一个非负整数数组，数组元素表示能跳跃的最大步数，比如，如果数组元素为3，就表示在当前位置向后可以跳跃1步、
 * 2步或者3步，题目求的是能否从第一个元素所在的位置跳跃到最后一个元素所在的位置
 *
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * https://www.bilibili.com/video/av55634887?from=search&seid=13393519030518090292
 */

public class JumpGame {

    /**
     * 时间复杂度：O(n*steps)
     * 空间复杂度：O(n)
     */
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) return false;
        int len = nums.length;
        int[] check = new int[len];
        // 0 unknown    1 reach end pos   2 unreach end pos
        check[len - 1] = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j <= nums[i]; j++) {
                if (check[i + j] == 1) {
                    check[i] = 1;
                    break;
                }
            }
        }
        return check[0] == 1;
    }

    /**
     * bottom-up
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean canJump02(int[] nums) {
        int len;
        if (null == nums || (len = nums.length) == 0) return false;
        int lastPos = len - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) lastPos = i;    // 不断更新lastPos
        }
        return lastPos == 0;
    }

    /**
     * top-down
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean canJump03(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) return false;
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

}
