package h128_LongestConsecutiveSequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 */

/**
 * 这个题目关于 最长连续序列：就是在数组中能找到一组数，它们是自然数的一个序列，如 2,3,4 或者 6,7,8,9这样的
 * 至于它们在数组中出现的顺序没有作要求，只要存在即可。
 * 另外，如果存在多个相等的元素，也只能算一次
 */
public class LongestConsecutiveSequence {

    /**
     * 解法1：排序
     * 首先检查输入的数组是否为空数组，如果是，函数直接返回 0 。对于其他情况，
     * 我们将 nums 数组排序，并考虑除了第一个数字以外的每个数字与它前一个数字的关系。
     *      如果当前数字和前一个数字相等，那么我们当前的序列既不会增长也不会中断，我们只需要继续考虑下一个数字。
     *      如果不相等，我们必须要检查当前数字是否能延长答案序列（也就是 nums[i] == nums[i-1] + 1）。如果可以增长，
     * 那么我们将当前数字添加到当前序列并继续。否则，当前序列被中断，我们记录当前序列的长度并将序列长度重置为 1 。
     *
     * 时间复杂度：O(N*logN)
     * 空间复杂度：O(1)或者O(N) 取决于排序算法能否允许就地排序
     */
    public int longestConsecutive(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        Arrays.sort(nums);
        int curLength = 1;
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] - nums[i - 1] == 1) {
                    curLength++;
                } else {
                    curLength = 1;
                }
                maxLength = Math.max(curLength, maxLength);
            }
        }
        return maxLength;
    }

    /**
     * 解法2：哈希表和线性空间的构造
     *
     * 时间复杂度：O(n)
     *      尽管在 for 循环中嵌套了一个 while 循环，时间复杂度看起来像是二次方级别的。但其实它是线性的算法。
     * 因为只有当 curNum 遇到了一个序列的开始， while 循环才会被执行（也就是 currentNum-1 不在数组 nums 里），
     * while 循环在整个运行过程中只会被迭代 n 次。这意味着尽管看起来时间复杂度为 O(N^2) ，实际这个嵌套循环只会
     * 运行 O(n + n) = O(n) 次。所有的计算都是线性时间的，所以总的时间复杂度是 O(n) 的。
     *
     * 空间复杂度：O(n)
     *      为了实现 O(1) 的查询，我们对哈希表分配线性空间，以保存 nums 数组中的 O(n) 个数字。除此以外，
     * 所需空间与暴力解法一致。
     */
    public int longestConsecutive02(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int curLength;
        int maxLength = 1;
        for (int curNum : set) {
            if (!set.contains(curNum - 1)) { // 表明curNum可以作为起点
                curLength = 1;
                while (set.contains(++curNum)) {
                    curLength++;
                }
                maxLength = Math.max(maxLength, curLength);
            }
        }
        return maxLength;
    }
}
