package m34_FindFirstAndLastPositionOfElementInSortedArray;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 题目链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 方法1
     */
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findFirst(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, last};
        }
    }

    // 找出 target 第一次出现的位置
    private int findFirst(int[] nums, int target) {
        int left = 0;
        // 注意 left 的初始值。因为是要找的是target第一次出现的位置，如果这个元素比数组中所有的元素都大，那么返回 nums.length
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 解释一下：当 nums[mid] > target 时，mid仍然有可能会是解。
                // 因为如果 目标值在数组中不存在，那么第一个大于它的值的索引就会被返回
                right = mid;
            }
        }
        return left;
    }

    /**
     * 解法2
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return res;
        }
        res[0] = findFirstPosition(nums, target);
        res[1] = findLastPosition(nums, target);
        return res;
    }

    private int findFirstPosition(int[] nums, int target) { // 找到target出现的第一个位置
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {  // 找到target出现的最后一个位置
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2; // 注意
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

}
