package m220_ContainsDuplicateIII;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
 * the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j
 * is at most k.
 * （两个值最多相差t，两个值对应的索引最多相差k）
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */

public class ContainsDuplicateIII {

    /**
     * 这种解法是参照前面219和220的解法，但是这里要考虑的问题不只是索引之间的距离，还需要考虑索引对应的值之间的差值的绝对值，
     * 这就产生了一个问题，索引对应的值都为int型，但是这两个值相减以后可能会超过int类型的最大值或者小于最小值，这样就会出错。
     * 所以需要将索引对应的数转换为long型的数，再进行计算。
     * 同时发现：long x = Integer.MAX_VALUE + 2; 像这样的计算，尽管转换成了long型，还是会产生溢出，因为是先计算，再进行转换，
     * 在计算的时候就会产生溢出，如果要改正，需要先将其中一个数转换成long型，这样就不会溢出，如long x = Integer.MAX_VALUE + 2;
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (null == nums || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long)nums[i] - (long)nums[j]) <= t) return true;
            }
        }
        return false;
    }

    /**
     * 利用滑动窗口的思想
     * 同时借助于TreeSet的floor方法和ceiling方法：floor(e),返回小于或等于e的最大元素; ceiling(e),返回大于或等于e的最小元素.
     * 维护一个大小为k的窗口，对于数组中的每个元素e，在这个窗口中查找是否有在[e-t, e+t]里面的元素，若有且满足一定关系，则返回true.
     * 若没有，则继续查找并动态更新窗口.
     * 注意：1、数组中的元素在进行加减运算可能会溢出，所以需要转化为Long型；2、窗口中元素的添加与删除要放在后面，如果放在前面
     * 就会出错，例如[1,2,3,4]这个数组，如果窗口大小为2（即k=2），那么当窗口为[1,2]时，此时计算的num[i]=2，实际上应该要计算
     * num[i]=3，因为num[i]=3才是与前面两个元素的索引距离小于或等于2。
     *
     * 时间复杂度：O(NlogK)  TreeSet的插入与删除都是logK的时间复杂度
     * 空间复杂度：O(K)
     */
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (null == nums || nums.length == 0 || k < 1 || t < 0) return false;
        TreeSet<Long> window = new TreeSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            Long floor = window.floor((long)nums[i] + t);
            Long ceil = window.ceiling((long)nums[i] - t);
            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) return true;
            window.add((long)nums[i]);
            if (i >= k) {
                window.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2147483647,-2147483647};
        int[] nums = new int[]{-1,2147483647};
        System.out.println(containsNearbyAlmostDuplicate(nums, 1, 2147483647));
        System.out.println(containsNearbyAlmostDuplicate2(nums, 1, 2147483647));
    }

}
