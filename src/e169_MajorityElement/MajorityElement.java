package e169_MajorityElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 链接：https://leetcode-cn.com/problems/majority-element
 */
public class MajorityElement {

    /**
     * 推荐解法：Boyer-Moore 投票算法
     */
    public int majorityElement(int[] nums) {
        Integer candidate = null;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }


    /**
     * 解法1：Brute Force
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int majorityElement01(int[] nums) {
        for (int num : nums) {
            int count = 0;
            for (int temp : nums) {
                if (num == temp) count++;
            }
            if (count > nums.length / 2) return num;
        }
        return -1;
    }

    /**
     * 解法2：HashMap
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public int majorityElement02(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int res = -1;
        for (int elem : nums) {
            if (!counts.containsKey(elem)) counts.put(elem, 1);
            else counts.put(elem, counts.get(elem) + 1);

            if (counts.get(elem) > nums.length / 2) {
                res = elem;
                break;
            }
        }
        return res;
    }

    /**
     * 解法3：Sorting
     *
     * 时间复杂度：O(N * logN)
     * 空间复杂度：O(1)
     */
    public int majorityElement03(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
