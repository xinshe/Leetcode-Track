package m503_NextGreaterElementII;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的
 * 下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *      输入: [1,2,1]
 *      输出: [2,-1,2]
 *      解释: 第一个 1 的下一个更大的数是 2；
 *          数字 2 找不到下一个更大的数；
 *          第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 *
 * 注意: 输入数组的长度不会超过 10000。
 *
 */

public class NextGreaterElementII {

    /**
     * 与 739. Daily Temperatures (Medium) 不同的是，数组是循环数组，并且最后要求的不是距离而是下一个元素。
     *
     * 维护一个单调栈，栈中的元素从栈顶到栈底是单调不降的。当我们遇到一个新的元素 A[i] 时，我们判断栈顶元素
     * 是否小于 A[i]，如果是，那么栈顶元素的下一个更大元素即为 A[i]，我们将栈顶元素出栈，并记录该栈顶元素的
     * 下一个比它大的元素为 A[i]； 如果不是，我们将 A[i] 入栈，保持栈的单调性，并对接下来的 A[i + 1],
     * A[i + 2] ... 执行同样的操作。
     *
     * 由于这道题的数组是循环数组，因此我们需要将每个元素都入栈两次。这样可能会有元素出栈找过一次，即得到了超过
     * 一个“下一个更大元素”，我们只需要保留第一个出栈的结果即可。
     *
     * 复杂度分析
     *      时间复杂度：O(N)。
     *      空间复杂度：O(N)。
     *
     */
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int len = nums.length;
        int[] next = new int[len];
        Arrays.fill(next, -1);
        Stack<Integer> indexes = new Stack<>();
        for (int i = 0; i < 2 * len; i++) {
            int num = nums[i % len];
            while (!indexes.isEmpty() && num > nums[indexes.peek()]) {
                next[indexes.pop()] = num;
            }
            if (i < len) {
                indexes.push(i);
            }
        }
        return next;
    }
}
