package e167_TwoSumII;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *      输入: numbers = [2, 7, 11, 15], target = 9
 *      输出: [1,2]
 *      解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 */
public class TwoSumII {

    /**
     * 方法1：写两个循环遍历数组即可
     * 时间复杂度O(n^2)
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        int len = numbers.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = i+1; j < len; j++) {
                int sum = numbers[i] + numbers[j];
                if (sum == target) {
                    res[0] = i+1;
                    res[1] = j+1;
                    return res;
                } else if (sum > target)
                    break;
            }
        }
        return res;
    }

    /**
     * 方法2：使用双指针（推荐）
     * 一个从头开始遍历,记索引为head; 一个从末尾开始遍历，记索引为tail.
     * sum = numbers[head] + numbers[tail]
     * 与target进行比较即可
     * 时间复杂度：O(n)
     */
    public static int[] twoSum02(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;
        while (head < tail) {   // 此处代码如果还要简化，可以将对sum的判断放入循环中进行处理
            int sum = numbers[head] + numbers[tail];
            if (sum == target)
                return new int[]{head+1, tail+1};
            else if (sum < target)
                head++;
            else
                tail--;
        }
        return null;
    }

    /**
     * 方法3：利用二分查找
     * 时间复杂度：O(nlogn)
     */
    public static int[] twoSum03(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int j = binarySearch(numbers, 0, numbers.length-1, target-numbers[i]);
            if (j > 0) {
                return new int[]{i+1, j+1};
            }
        }
        return null;
    }

    private static int binarySearch(int[] numbers, int low, int high, int key) {
        while (low <= high) {
            int mid = (low+high)/2;
            if (numbers[mid] > key)
                high = mid - 1;
            else if (numbers[mid] < key)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2,7,11,15};
        System.out.print(Arrays.toString(twoSum03(numbers, 9)));
    }
}
