package m215_KthLargestElementInAnArray;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *      输入: [3,2,1,5,6,4] 和 k = 2
 *      输出: 5
 *
 * 示例 2:
 *      输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 *      输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 题目链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 */
public class KthLargestElementInAnArray {

    /**
     * 方法1：利用自己实现的最小堆 来实现
     * 时间复杂度：O(NlogK)
     * 空间复杂度：O(K)
     */
    public int findKthLargest(int[] nums, int k) {
        int[] minHeap = new int[k];
        int len = nums.length;
        for (int i = 0; i < k; i++) {
            minHeap[i] = nums[i];
        }
        for (int i = k/2-1; i >= 0; i--) {
            shiftDown(minHeap, i, k);
        }
        for (int i = k; i < len; i++) {
            if (nums[i] > minHeap[0]) {
                minHeap[0] = nums[i];
                shiftDown(minHeap,0, k);
            }
        }
        return minHeap[0];
    }

    private void shiftDown(int[] minHeap, int i, int k) {
        // 这个方法还可以做优化：就是不用每次都做交换，记住索引，最后交换一次即可
        while (2*i+1 < k) {
            int j = 2*i+1; // 节点i的左子节点为j=2*i+1
            if (j + 1 < k && minHeap[j] > minHeap[j+1]) { // 找到节点i的左右子节点的较小者
                j++;
            }
            if (minHeap[i] <= minHeap[j]) { // 用节点i与其左右子节点的较小者进行比较
                break;
            } else {
                int tmp = minHeap[i];
                minHeap[i] = minHeap[j];
                minHeap[j] = tmp;
            }
            i = j;  // 这里赋的值是值较小的孩子节点的索引
        }
    }

    /**
     * 方法2：同方法1思路一致
     *      建一个小顶堆
     *
     * 时间复杂度 O(NlogK)
     * 空间复杂度 O(K)
     */
    public int findKthLargest03(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(); // 小顶堆
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * 方法3：排序
     * 时间复杂度 O(NlogN)
     * 空间复杂度 O(1)
     */
    public int findKthLargest02(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 方法4：快速选择（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 快速排序参考资料：https://blog.csdn.net/morewindows/article/details/6684558
     * 快速选择思想，时间复杂度分析：https://blog.csdn.net/Yaokai_AssultMaster/article/details/68878950
     */
    public int findKthLargest04(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        k = nums.length - k;    // 排序一般是从小到大进行排序，第k大的应该是倒数第k个
        while (left < right) {
            int pivot = partition02(nums, left, right);
            if (pivot < k) left = pivot + 1;
            else if (pivot > k) right = pivot - 1;
            else return nums[pivot];
        }
        return nums[k];
    }

    /**
     * 一趟快排的实现
     * 这里left<right要不要取等号，考虑一下！
     * 另外与nums[pivot]相等的元素放到哪一边，考虑一下！
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = left;
        int tmp;
        while (left < right) {  // 这里不取等号
            while (left < right && nums[right] >= nums[pivot]) right--;
            while (left < right && nums[left] < nums[pivot]) left++;
            if (left >= right) break;
            tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
        tmp = nums[pivot];
        nums[pivot] = nums[right];
        nums[right] = tmp;
        return right;
    }

    /**
     * 一趟快排的另一种实现（通用写法）
     * 如果取的flag是最左边的元素，那么就要从最右边开始遍历；反之。
     */
    private int partition02(int[] nums, int left, int right) {
        int flag = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= flag)  right--;
            if (left < right) nums[left] = nums[right];
            while (left < right && nums[left] < flag) left++;
            if (left < right) nums[right] = nums[left];
        }
        nums[left] = flag;
        return left;
    }

}
