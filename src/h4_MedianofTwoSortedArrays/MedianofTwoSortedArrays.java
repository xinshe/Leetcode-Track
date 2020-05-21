package h4_MedianofTwoSortedArrays;

/**
 * 4. 寻找两个有序数组的中位数
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 *
 */

public class MedianofTwoSortedArrays {

	// 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;

		int left = (len1 + len2 + 1) / 2;
		int right = (len1 +len2 + 2) / 2;
		return (findKMin(nums1, 0, len1 - 1, len1, nums2, 0, len2 - 1, len2, left)
				+ findKMin(nums1, 0, len1 - 1, len1, nums2, 0, len2 - 1, len2, right)) * 0.5;

	}

	// 找到第k小的数
	private double findKMin(int[] nums1, int start1, int end1, int len1,
							int[] nums2, int start2, int end2, int len2, int k) {

		if (len1 > len2) {
			return findKMin(nums2, start2, end2, len2, nums1, start1, end1, len1, k);
		}
		if (len1 == 0) {
			return nums2[start2 + k - 1];
		}
		if (k == 1) {
			return Math.min(nums1[start1], nums2[start2]);
		}

		int i = start1 + Math.min(len1, k / 2) - 1;
		int j = start2 + Math.min(len2, k / 2) - 1;
		if (nums1[i] > nums2[j]) {
			return findKMin(nums1, start1, end1, len1,
							nums2, j + 1, end2, len2 - (j - start2 + 1), k - (j - start2 + 1));
		} else {
			return findKMin(nums1, i + 1, end1, len1 - (i - start1 + 1),
							nums2, start2, end2, len2, k -(i - start1 + 1));
		}
	}

	// 时间复杂度 O(m + n)
	public static double findMedianSortedArrays02(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		int pm = 0;
		int pn = 0;

		double[] res = new double[m+n];
		int index = 0;
		while(pm < m && pn < n) {
			if(nums1[pm] <= nums2[pn])
				res[index++] = nums1[pm++];
			else
				res[index++] = nums2[pn++];
		}
		while(pm < m) {
			res[index++] = nums1[pm++];
		}
		while(pn < n) {
			res[index++] = nums2[pn++];
		}
		
		if((m+n)%2 == 0) {
			return (res[(m+n)/2]+res[(m+n)/2-1])/2;
		} else {
			return res[(m+n)/2];
		}
    }

}
