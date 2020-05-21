package e88_MergeSortedArray;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *      初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 *      你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 */
public class MergeSortedArray {

    /**
     * 解法1：
     * 需要从尾开始遍历
     * 分析一下为什么不能从从头开始遍历：如果从头开始遍历的话，如果nums1[i]<=nums2[j]，那么只需要i++,j保持
     * 不变；如果nums1[i]>nums2[j]，那么需要将交换nums1[i]和nums2[j]的值，并且i++，j保持不变，但是这里
     * 有一个问题，交换后的nums2数组里的元素不一定是有序的，所以还需要对进行排序，这样时间复杂度变成了O(m*n)
     * 所以需要从尾部开始遍历
     * 下面这种解法实际上是合并两个数组的一种比较通用的解法
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int indexMerge = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] >= nums2[index2])
                nums1[indexMerge--] = nums1[index1--];
            else
                nums1[indexMerge--] = nums2[index2--];
        }
//        while (index1 >= 0) {
//            nums1[indexMerge--] = nums1[index1--];
//        }
        while (index2 >= 0) {
            nums1[indexMerge--] = nums2[index2--];
        }
    }

    /**
     * 解法2：
     * 时间复杂度O(m+n)
     *
     * 它也是从尾部开始遍历
     * 它主要考虑的是什么情况下要用nums1数组的值，什么情况下要用nums2数组的值
     * 当index2<0时，自然是用nums1的值
     */
    public static void merge02(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int indexMerge = m + n - 1;
        while (indexMerge >= 0) {
            nums1[indexMerge--] = index2 < 0 || (index1 >= 0 && nums1[index1] > nums2[index2])
                    ? nums1[index1--] : nums2[index2--];
//            nums1[indexMerge--] = index1 < 0 || (index2 >= 0 && nums1[index1] < nums2[index2])
//                    ? nums2[index2--] : nums1[index1--];
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,2,3,0,0,0};
//        int[] nums2 = new int[]{2,5,6};
//        merge(nums1, 3, nums2, 3);

//        int[] nums1 = new int[]{1};
//        int[] nums2 = new int[]{};
//        merge(nums1, 1, nums2, 0);

        int[] nums1 = new int[]{4,5,6,0,0,0};
        int[] nums2 = new int[]{1,2,3};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
