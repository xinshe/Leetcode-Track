package m334_IncreasingTripletSubsequence;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * 数学表达式如下:
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 *
 * Example 1:
 * Input: [1,2,3,4,5]
 * Output: true
 *
 * Example 2:
 * Input: [5,4,3,2,1]
 * Output: false
 *
 */
public class IncreasingTripletSubsequence {

    /**
     * 思路：
     * 首先找到一个相对小的值，然后找到比这个小一点的值大的值(中间值)，然后看能够在最后找到比中间值大的值。
     *
     * 为什么这种思路能保证覆盖所有的情况？
     * 首先，如果只有一个最小值，然后找不到中间值，那么这个数组必然不包含递增的三个数（因为连递增的两个数都找不到）。
     *
     * 然后，假设我们找到了两个递增的值，那么如果下一个值小于最小值，我们就应该将最小值的指针定位到这个值上。
     * 我们尽可能的使用最小值，防止后面出现了更小的一对递增值，而即使不出现，也不妨碍我们找到解（因为最终是看能否
     * 找到大于中间值的值）。
     * 如果下一个值大于最小值，且小于中间值，则我们使用该值作为中间值(因为如果最小的中间值都得不到解，那么就是false，
     * 这样也保证了覆盖所有的情况)。
     *
     * 最后，如果找到了大于中间值的值，则为true.
     *
     * 时间复杂度为 O(n)，空间复杂度为 O(1)
     */
    public boolean increasingTriplet(int[] nums) {
        if (null == nums || nums.length <= 2) return false;
        int left = Integer.MAX_VALUE; // 较小值
        int middle = Integer.MAX_VALUE; // 中间值
        for (int num : nums) {
            if (num <= left) left = num;
            else if (num <= middle) middle = num;
            else return true;
        }
        return false;
    }

    /**
     * 这里其实可以扩展到更一般的情况：找到递增的n元子序列
     * 用一个n-1的一维数组处理即可。思路同上
     */
    public boolean increasingTriplet02(int[] nums) {
        if (null == nums || nums.length <= 2) return false;
        int[] incArr = new int[2];
        for (int i = 0; i < 2; i++) {
            incArr[i] = Integer.MAX_VALUE;
        }
        for (int num : nums) {
            for (int i = 0; i < 2; i++) {
                if (num <= incArr[i]) {
                    incArr[i] = num;
                    break;
                }
            }
            if (num > incArr[1]) return true;
        }
        return false;
    }

}
