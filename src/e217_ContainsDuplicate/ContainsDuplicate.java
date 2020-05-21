package e217_ContainsDuplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 */

public class ContainsDuplicate {

    /**
     * 方法1：排序法
     *
     * 时间复杂度 O(NlogN)
     * 空间复杂度 O(1)
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    /**
     * 方法2：Hash Table方法
     *
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Set<Integer> set = new HashSet<>(nums.length);
        for (int elem : nums) {
            if (set.contains(elem)) return true;
            set.add(elem);
        }
        return false;
    }
}
