package m739_DailyTemperatures;

import java.util.Stack;

/**
 * 739 每日温度
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 题目链接：https://leetcode-cn.com/problems/daily-temperatures/
 *
 * 类似题目：
 * 1. 给定一个数组，对于每一个数，求其后面第一个大于它的数
 * 2. 给定一组数，针对每个数，寻找它和它右边第一个比它大的数之间有多少个数。
 */

public class DailyTemperatures {

    /**
     * 方法1：单调栈
     * 在遍历数组时，用栈把数组中的数存起来。
     * 如果当前遍历的数比栈顶元素来的大，说明栈顶元素的下一个比它大的数就是当前元素。
     *
     * 复杂度分析：
     *      时间复杂度：O(N)。其中 N 是 T 的长度，每个索引最多做一次压栈和出栈的操作。
     *      空间复杂度：O(W)，其中 W 是 T[i] 的可取值的数目。
     *
     */
    public int[] dailyTemperatures(int[] T) {
        if (null == T || T.length == 0) return null;
        int len = T.length;
        int[] dist = new int[len];
        Stack<Integer> indexes = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!indexes.isEmpty() && T[i] > T[indexes.peek()]) {
                int preIndex = indexes.pop();
                dist[preIndex] = i - preIndex;
            }
            indexes.push(i);
        }
        return dist;
    }
}
