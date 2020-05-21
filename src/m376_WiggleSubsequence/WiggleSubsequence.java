package m376_WiggleSubsequence;

/**
 * 376. 摆动序列
 *
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
 * 少于两个元素的序列也是摆动序列。
 *
 * 例如，[1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5]
 * 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。通过从原始序列中删除一些（也可以不删除）元素来获得子序列，
 * 剩下的元素保持其原始顺序。
 *
 * 示例1:
 *      输入: [1,7,4,9,2,5]
 *      输出: 6
 *      解释: 整个序列均为摆动序列。
 *
 * 示例2:
 *      输入: [1,17,5,10,13,15,10,5,16,8]
 *      输出: 7
 *      解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 *
 * 示例3:
 *      输入: [1,2,3,4,5,6,7,8,9]
 *      输出: 2
 *
 * 进阶:
 *      你能否用 O(n) 时间复杂度完成此题?
 *
 * 参考：https://leetcode-cn.com/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode/
 */
public class WiggleSubsequence {

    /**
     * 解法1：DP
     *
     * 算法：
     *      为了更好地理解这一方法，用两个数组来 dp ，分别记作 up 和 down。
     *      每当我们选择一个元素作为摆动序列的一部分时，这个元素要么是上升的，要么是下降的，这取决于前一个元素的大小。
     *      up[i] 存的是目前为止最长的以第 i 个元素结尾的上升摆动序列的长度。
     *      类似的，down[i] 记录的是目前为止最长的以第 i 个元素结尾的下降摆动序列的长度。
     *      我们每当找到将第 i 个元素作为上升摆动序列的尾部的时候就更新 up[i] 。现在我们考虑如何更新 up[i]，
     *      我们需要考虑前面所有的降序结尾摆动序列，也就是找到 down[j] ，满足 j < i 且 nums[i] >nums[j]。
     *      类似的， down[i] 也会被更新。
     *
     * 复杂度分析：
     *      时间复杂度： O(n^2)。 循环内嵌套了一个循环。
     *      空间复杂度： O(n)。 dp 需要两个同样长度的数组。
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    /**
     * 解法2：线性动态规划
     *
     * 复杂度分析
     *      时间复杂度： O(n)。只需要遍历数组一遍。
     *      空间复杂度： O(n)。 dp需要两个相同长度的数组。
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = 1; down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    /**
     * 解法3：空间优化的动态规划
     *
     * 复杂度分析
     *      时间复杂度： O(n)。仅遍历数组一次。
     *      空间复杂度： O(1)。只使用了常数级别的额外空间。
     */
    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }

    /**
     * 解法4：贪心
     */
    public int wiggleMaxLength4(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;
        int prediff = nums[1] - nums[0];
        int count = prediff == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff < 0 && prediff >= 0 || diff > 0 && prediff <= 0) {
                count++;
                prediff = diff;
            }
        }
        return count;
    }
}
