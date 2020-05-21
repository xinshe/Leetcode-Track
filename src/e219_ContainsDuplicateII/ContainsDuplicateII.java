package e219_ContainsDuplicateII;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * （两个索引对应的值相等并且索引间的距离小于或等于k）
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */

public class ContainsDuplicateII {

    /**
     * 从头到尾的遍历
     * 时间复杂度 O(N*k)
     * 空间复杂度 O(1)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k; j++) {
                if (j < nums.length && nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    /**
     * 用Hash Set 来进行滑动窗口
     * 时间复杂度 O(N*logk)
     * 空间复杂度 O(k)
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return false;
        if (k <= 0) return false;
        Set<Integer> set = new HashSet<>(k+1); // 窗口大小为k+1，这样首尾元素索引值相隔k
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i-k-1]); // 从i为k+1开始，窗口进行滑动，删除窗口头部元素
            if (!set.add(nums[i])) return true; // 添加新元素到窗口尾部
        }
        return false;
    }

    /**
     * HashMap解法
     * 利用了HashMap的put方法，如果put了重复的键的话，将返回该键原来映射的值，并将新值替换旧值
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return false;
        if (k <= 0) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer oldValue = map.put(nums[i], i);
            if (oldValue != null && i - oldValue <= k) return true;
        }
        return false;
    }
}
