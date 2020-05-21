package m275_HIndexII;

/**
 * 因为题目给出的数组已排好序，又要求对数级别的时间复杂度，所以使用二分查找
 *
 * Time complexity : O(logN) since we apply binary search algorithm here.
 * Space complexity : O(1), it's a constant space solution.
 */
public class HIndexII {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int n = citations.length;
        int left = 0;
        int right = n - 1;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left)/2;
            if (citations[middle] == n - middle) return n - middle;
            else if(citations[middle] > n - middle) right = middle - 1;
            else left = middle + 1;
        }
        return n - left;
    }
}
