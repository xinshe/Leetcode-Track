package m556_NextGreaterElementIII;

/**
 * 556. 下一个更大元素 III
 *
 * 给定一个32 位正整数n，你需要找到最小的32位整数，其与n中存在的位数完全相同，并且其值大于n。
 * 如果不存在这样的32位整数，则返回-1。
 *
 * 示例 1:
 * 输入: 12
 * 输出: 21
 *
 * 示例 2:
 * 输入: 21
 * 输出: -1
 *
 * 题目链接：https://leetcode-cn.com/problems/next-greater-element-iii
 */
public class NextGreaterElementIII {

    /**
     * 参考：https://leetcode-cn.com/problems/next-greater-element-iii/solution/xia-yi-ge-geng-da-yuan-su-iii-by-leetcode/
     */
    public int nextGreaterElement(int n) {
        char[] arr = ("" + n).toCharArray();
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }

        int j = arr.length - 1;
        while (j >= 0 && arr[j] <= arr[i]) {
            j--;
        }

        swap(arr, i, j);
        reverse(arr, i + 1);

        try {
            return Integer.parseInt(new String(arr));
        } catch (Exception e) {
            return -1;
        }

    }

    private void reverse(char[] arr, int start) {
        int left = start;
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
