package m540_SingleElementInASortedArray;

/**
 * 540. 有序数组中的单一元素
 *
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 *
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 *
 */
public class SingleElementInASortedArray {

    /**
     * 算法：
     * 奇数长度的数组首尾元素索引都为偶数，因此我们可以将 left 和 right 设置为数组首尾。
     * 我们需要确保 mid 是偶数，如果为奇数，则将其减 1。
     * 然后，我们检查 mid 的元素是否与其后面的索引相同。
     * 如果相同，则我们知道 mid 不是单个元素。且单个元素在 mid 之后。则我们将 left 设置为 mid + 2。
     * 如果不是，则我们知道单个元素位于 mid，或者在 mid 之前。我们将 right 设置为 mid。
     * 一旦 left == right，则当前搜索空间为 1 个元素，那么该元素为单个元素，我们将返回它。
     *
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
