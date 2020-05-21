package m11_ContainerWithMostWater;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 * which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */

public class ContainerWithMostWater {

    /**
     * 双指针解法
     * 不断剔除短板
     *
     * 参考：https://www.bilibili.com/video/av32030054?from=search&seid=15433268134973924992
     */
    public int maxArea(int[] height) {
        if (null == height || height.length <= 1) return 0;
        int left = 0;
        int right = height.length - 1;
        int ans = 0, h;
        while (left < right) {
            h = Math.min(height[left], height[right]);
            ans = Math.max(ans, h * (right - left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        return ans;
    }
}
