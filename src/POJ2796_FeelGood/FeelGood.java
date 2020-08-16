package POJ2796_FeelGood;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *
 * 题目大意：给出一组数字，求一区间，使得 区间的元素之和 与 区间内的最小值 的乘积最大，
 * 求出这个最大乘积 和 区间的左右端点。
 *
 * 输入：
 * 6
 * 3 1 6 4 5 2
 *
 * 输出：
 * 60
 * 2 4
 *
 * 题目链接：http://poj.org/problem?id=2796
 *
 */
public class FeelGood {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            if (i > 0) {
                sum[i] = sum[i - 1] + nums[i];
            } else {
                sum[i] = nums[i];
            }
        }

        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxProduct = 0;
        int maxLeft = -1;
        int maxRight = n;
        for (int i = 0; i < n; i++) {
            int leftSum = left[i] == -1 ? 0 : sum[left[i]];
            int curSum = sum[right[i] - 1] - leftSum;
            if (curSum * nums[i] > maxProduct) {
                maxProduct = curSum * nums[i];
                maxLeft = left[i];
                maxRight = right[i];
            }
        }
        System.out.println(maxProduct + " " + (maxLeft + 1) + " " + (maxRight - 1));
    }
}
