package e283_MoveZeroes;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *      输入: [0,1,0,3,12]
 *      输出: [1,3,12,0,0]
 *
 * 说明:
 *      必须在原数组上操作，不能拷贝额外的数组。
 *      尽量减少操作次数。
 *
 */

public class MoveZeroes {

    /**
     * 方法1：双指针思想
     *
     * 复杂度分析：
     *      时间复杂度：O(n)。但是，操作仍然是局部优化的。代码执行的总操作（数组写入）为 n（元素总数）。
     *      空间复杂度：O(1)，只使用常量空间。
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx < nums.length) {
            nums[idx++] = 0;
        }
    }

    /**
     * 方法2：最优解（但是运行时间比方法1要慢，很奇怪）——双指针思想
     *
     * 复杂度分析：
     *      时间复杂度：O(n)。但是，操作是最优的。代码执行的总操作（数组写入）是非 0 元素的数量。这比上一个解决方案的复杂性
     *          （当大多数元素为 0 时）要好得多。但是，两种算法的最坏情况（当所有元素都为非 0 时）复杂性是相同的。
     *      空间复杂度：O(1)，只使用了常量空间。
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int p = 0, cur = 0;
        while (cur < nums.length) {
            if (nums[cur] != 0) {
                int tmp = nums[p];
                nums[p] = nums[cur];
                nums[cur] = tmp;
                p++;
            }
            cur++;
        }
    }
}
