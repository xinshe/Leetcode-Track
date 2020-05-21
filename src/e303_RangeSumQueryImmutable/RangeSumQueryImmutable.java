package e303_RangeSumQueryImmutable;

/**
 * 303. 区域和检索 - 数组不可变
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i, j 两点。
 *
 * 示例：
 *      给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *      sumRange(0, 2) -> 1
 *      sumRange(2, 5) -> -1
 *      sumRange(0, 5) -> -3
 *
 * 说明:
 *      1、你可以假设数组不可变。
 *      2、会多次调用 sumRange 方法。
 *
 */

public class RangeSumQueryImmutable {

    /**
     * 这里如果用二维数组来求解的话会超出内存限制
     */
    private static int[] sums;

    // 这里主要是写计算各区间nums并存储到sums的逻辑
    public RangeSumQueryImmutable(int[] nums) {
        sums = new int[nums.length];
        if (nums.length > 0)
            sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j] - (i == 0 ? 0 : sums[i-1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};

        RangeSumQueryImmutable obj = new RangeSumQueryImmutable(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }
}
