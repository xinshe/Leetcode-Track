package m75_SortColors;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 75. 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、
 * 蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */
public class SortColors {

    /**
     * 解法1：
     * just so so
     */
    public static void sortColors(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int index = 0;
        for (int key : map.keySet()) {
            for (int i = 0; i < map.get(key); i++)
                nums[index++] = key;
        }
    }

    /**
     * 解法2：
     * beat 100%
     */
    public static void sortColors02(int[] nums) {
        int index0 = 0, index1 = 0, index2 = 0; // index* 都是指向对应元素下一个可以放置的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[index2++] = 2;
                nums[index1++] = 1;
                nums[index0++] = 0;
            } else if (nums[i] == 1) {
                nums[index2++] = 2;
                nums[index1++] = 1;
            } else if (nums[i] == 2) {
                nums[index2++] = 2;
            }
        }
    }

    /**
     * 解法3：推荐此解法
     *
     * 思路：两个指针p1，p2，p1指向数组左端，p2指向数组右端，index为遍历指针
     * 从左至右依次遍历元素，若遍历到0，就将nums[index]与nums[p1]进行交换，p1++；
     * 若遍历到2，就将nums[index]与nums[p2]进行交换，p2--
     */
    public static void sortColors03(int[] nums) {
        int p1 = 0, p2 = nums.length-1, index = 0;
        while (index <= p2) {   // 这里为什么要取=，因为p2指向的元素是没有被遍历到并且确认了位置的
            if (nums[index] == 0) {
                nums[index++] = nums[p1];   // 这里index为什么要++
                nums[p1++] = 0;
            } else if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2--] = 2;
            } else {
                index++;
            }
        }
    }

    /**
     * 解法4：和解法3类似
     */
    public static void sortColors04(int[] nums) {
        int zero = 0, one = 0, two = nums.length - 1;
        while (one <= two) {
            if (nums[one] == 0) {
                swap(nums, zero++, one++);
            } else if (nums[one] == 2) {
                swap(nums, two--, one);
            } else {
                one++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        // [2,0,1]
        int[] nums = new int[]{2,0,1};
        sortColors03(nums);
        System.out.println(Arrays.toString(nums));
    }
}
