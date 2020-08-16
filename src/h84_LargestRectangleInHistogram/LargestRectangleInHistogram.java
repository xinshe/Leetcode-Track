package h84_LargestRectangleInHistogram;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 见 histogram.png
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 见 histogram_area.png
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为10个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 题目链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 *
 * 参考：
 * 解法1-2：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
 * 解法3：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/xiang-jie-dan-diao-zhan-bi-xu-miao-dong-by-sweetie/
 */
public class LargestRectangleInHistogram {

    /**
     * 解法1：单调栈
     * 对于每根柱子，找到其左右两边第一个 高度大于等于它的柱子
     *
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     */
    public int largestRectangleArea(int[] heights) {
        int len = 0;
        if (null == heights || (len = heights.length) == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int[] left = new int[len];
        int[] right = new int[len];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) { // 对于每个元素，找到其左边第一个小于它的元素
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) { // 注意 <=
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek(); // 注意 -1  含义是 可以向左延伸的范围
            stack.push(i);
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) { // 对于每个元素，找到其右边第一个小于它的元素
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) { // 注意 <=
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();    // 注意 len  含义是可以向右延伸的范围
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    /**
     * 解法2：单调栈 + 常数优化
     */
    public int largestRectangleArea2(int[] heights) {
        int len = 0;
        if (null == heights || (len = heights.length) == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    /**
     * 解法3
     */
    public int largestRectangleArea3(int[] heights) {
        int len = 0;
        if (null == heights || (len = heights.length) == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[len + 2];
        System.arraycopy(heights, 0, tmp, 1, len);

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 这里计算的是以栈顶柱体为高的矩形的面积，它的左边是 stack.peek()对应的柱体，右边是 i 对应的柱体，高度均比栈顶元素要小
            // 对栈中柱体来说，栈中的前面的柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int h = tmp[stack.pop()];
                maxArea = Math.max(maxArea, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
