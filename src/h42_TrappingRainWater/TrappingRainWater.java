package h42_TrappingRainWater;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 参考视频：https://www.bilibili.com/video/av60668163/?p=1
 */

public class TrappingRainWater {

    /**
     * 方法1：DP
     * 分析：对每个柱子找到其左右两边最高的柱子，则这个柱子能容纳的水的体积就是
     * min(max_left， max_right) - curHeight
     * <p>
     * 对每个柱子:
     * 从左往右扫描一遍，记录其左侧柱高的最大值
     * 从右往左扫描一遍，记录其右侧柱高的最大值
     * 再从头到尾扫一遍，累加每个柱子可容纳的体积
     * <p>
     * 三次遍历，时间复杂度O(n)，空间复杂度O(n)
     * 参考：https://www.jianshu.com/p/135b764b751f
     */
    public int trap(int[] height) {
        int len;
        if (height == null || (len = height.length) <= 2) return 0;
        int[] leftMax = new int[len];   // leftMax[i]表示在i位置左侧柱高的最大值
        int[] rightMax = new int[len];  // rightMax[i]表示在i位置右侧柱高的最大值

        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);   // 记录从1 - (len-1)
            rightMax[len - 1 - i] = Math.max(rightMax[len - i], height[len - i]); // 记录从0 - (len-2)
        }

        int res = 0;
        int min;
        for (int i = 0; i < len; i++) {
            min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i]) res += (min - height[i]); //若当前柱子左右两侧最高高度的最小值比当前柱子高度高，则该柱上面缺失的部分都是存水的体积
        }

        return res;
    }

    /**
     * 方法2：双指针解法
     * 基于上面的DP解法，我们可以知道，对于某个位置i能积多少水，取决于它的左右两边的柱子高度的最大值的较小的那个，即
     * min(leftMax[i], rightMax[i])，所以我们只需要维护一个leftMax和rightMax就可以了，每次进行积水计算时，只需要用
     * min(leftMax, rightMax)进行计算就可以了。所以，由此产生了双指针的解法。
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public int trap02(int[] height) {
        int len;
        if (height == null || (len = height.length) <= 2) return 0;
        int left = 0;
        int right = len - 1;
        int leftMax = 0;
        int rightMax = len - 1;
        int res = 0;
        while (left < right) {
            // 对于某个位置i能积多少水，取决于它的左右两边的柱子高度的最大值的较小的那个，所以当height[left] < height[right]时，就对左边进行操作
            if (height[left] < height[right]) {
                if (height[left] > height[leftMax]) leftMax = left; // 如果当前位置是最高的，更新leftMax
                else res += (height[leftMax] - height[left]); // 否则，计算当前位置积水，并累加
                left++;
            } else {
                if (height[right] > height[rightMax]) rightMax = right;
                else res += (height[rightMax] - height[right]);
                right--;
            }
        }
        return res;
    }

    /**
     * 方法3：单调栈
     * 我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前
     * 的条形块被栈中的前一个条形块界定。如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和
     * 栈的前一个条形块界定，因此我们可以弹出栈顶元素并且累加答案到 res。
     *
     * 使用栈来存储条形块的索引下标。
     * 遍历数组：对于每一个柱子i
     *      当栈非空且 height[i] > height[stack.peek()]
     *          意味着栈中元素可以被弹出，弹出栈顶元素 cur，表明cur位置处能够积水
     *          计算当前元素和栈顶元素的距离，准备进行填充操作 width = i - stack.peek() - 1
     *          找出界定高度 heightDiff = Math.min(height[stack.peek()], height[i]) - height[cur]
     *          往答案中累加积水量 res += width * heightDiff;
     *      将当前索引下标入栈
     *      将 i 移动到下个位置
     *
     * 复杂性分析：
     * 时间复杂度：O(n)。
     *      单次遍历 O(n)，每个条形块最多访问两次（由于栈的弹入和弹出），并且弹入和弹出栈都是 O(1)的。
     * 空间复杂度：O(n)。 栈最多在阶梯型或平坦型条形块结构中占用 O(n) 的空间。
     */
    public int trap03(int[] height) {
        if (null == height || height.length <= 2) return 0;
        Stack<Integer> stack = new Stack<>(); // 单调递减栈
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop(); // 计算当前位置cur处的积水
                if (stack.empty()) break; // 当前位置cur的左侧没有柱子了，肯定不会产生积水了
                int width = i - stack.peek() - 1; // 积水的宽度。对于当前位置cur，i是右边比它高的柱子，stack.peek()是左边比它高的柱子，一定要是这两个值相减，不能是i-cur作为宽度，因为这样就少算了cur位置左边的柱子能带来的积水宽度了
                int heightDiff = Math.min(height[stack.peek()], height[i]) - height[cur]; // 积水的高度。等于当前位置cur左右两边第一个比它高的柱子的较小值 减去 它自己的柱子高度
                res += width * heightDiff;
            }
            stack.push(i);
        }
        return res;
    }

}
