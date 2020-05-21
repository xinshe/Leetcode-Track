package m378_KthSmallestElementInASortedMatrix;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 *
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n^2 。
 *
 */

public class KthSmallestElementInASortedMatrix {

    /**
     * 解法1：二分查找
     *
     * 思路：
     *      1.找出二维矩阵中最小的数low，最大的数high，那么第k小的数必定在low~high之间
     *      2.mid = low + (high - low) / 2；在二维矩阵中寻找小于等于mid的元素个数cnt
     *      3.若这个cnt小于k，表明第k小的数在右半部分，即low=mid+1, high=high，又保证了第k小的数在low~high之间
     *      4.若这个cnt大于k，表明第k小的数在左半部分，即low=low, high=mid-1，又保证了第k小的数在low~high之间
     *      5.因为每次循环中都保证了第k小的数在low~high之间，当low==high时，第k小的数即被找出，等于low
     *
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;
        int m = matrix.length, n = matrix[0].length;
        int low = matrix[0][0], high = matrix[m - 1][n - 1];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n && matrix[i][j] <= mid; j++) {
                    cnt++;
                }
            }
            if (cnt < k) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}
