package e35_SearchInsertPosition;

/**
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的
 * 位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * 题目链接：https://leetcode-cn.com/problems/search-insert-position
 */
public class SearchInsertPosition {

    /**
     * 参考：https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;    // left和right的初始化表明：插入元素的位置可能是 [0, nums.length - 1]
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {   // 严格小于 target 的元素一定不是解。也就是说，mid位置一定不是target要插入的位置
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] > target) { // mid位置可能是target要插入的位置，所以不要写成 right = mid - 1
                // 下一轮搜索区间是 [left, mid]
                right = mid;
            } else {
                return mid;
            }
        }
        return left;
    }

    /**
     * 另一种写法
     */
    public int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        // 因为有可能数组的最后一个元素的位置的下一个是我们要找的，故右边界是 len
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {   // 严格小于 target 的元素一定不是解
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 下一轮搜索区间是 [left, mid]
                right = mid;
            } else {
                return mid;
            }
        }
        return left;
    }
}
