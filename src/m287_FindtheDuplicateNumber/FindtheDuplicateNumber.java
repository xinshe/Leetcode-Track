package m287_FindtheDuplicateNumber;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at
 * least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */

public class FindtheDuplicateNumber {


    /**
     * 方法1：弗洛伊德的乌龟和兔子（循环检测）
     * 跟判断单链表是否有环的思路一样。
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * 参考：https://blog.csdn.net/monkeyduck/article/details/50439840
     *
     * 见包中的图：令|S->O|=a，|O->X|=c，|X->O|=x
     * 当快慢指针汇合时，
     * 慢指针走过的距离为：a+c
     * 快指针走过的距离为：a+c+x+c
     * 由于快指针的速度是慢指针的2倍，所有汇合时快指针走过的距离是慢指针走过的距离的2倍，即：a+c+x+c=2*(a+c)，那么x=a。
     * 所以此时若再设置两个指针p1和p2，p1从开始处S往后走每次走一步，p2从汇合处X往后走每次走一步，一定能在入口处O相遇。
     *
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        // 第一步：找到快慢指针的汇合点
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 第二步：找到环的入口
        fast = 0; // fast指向开始的位置
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 方法2：二分查找法
     *
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    public int findDuplicate2(int[] nums) {
       if (nums == null || nums.length <= 1) return 0;
       int low = 1;
       int high = nums.length - 1;
       while (low <= high) {
           int mid = low + (high - low) / 2;
           int cnt = 0;
           for (int num : nums) {
               if (num <= mid) cnt++;
           }
           if (cnt > mid) high = mid - 1;
           else low = mid + 1;
       }
       return low;
    }

        /**
         * 方法3：排序（不满足题目的约束条件）
         *
         * 时间复杂度：O(N*logN)
         * 空间复杂度：O(1)或者O(N)
         */

    /**
     * 方法4：利用集合set（不满足题目的约束条件）
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */

}
