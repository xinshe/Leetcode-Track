package e645_SetMismatch;


import java.util.Arrays;

public class SetMismatch {

    /**
     * 方法1：开辟一个数组，记录每个元素出现的个数
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length <= 1) return null;
        int[] res = new int[2];
        int[] cnts = new int[nums.length];
        for (int num : nums) {
            cnts[num - 1]++;
        }
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] == 2) res[0] = i + 1;
            else if (cnts[i] == 0) res[1] = i + 1;
        }
        return res;
    }

    /**
     * 方法2：排序
     *
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    public int[] findErrorNums2(int[] nums) {
        if (nums == null || nums.length <= 1) return null;
        Arrays.sort(nums);
        int same = 1;
        int lack = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i+1]) same = nums[i];
            if (nums[i] == lack) lack++;
        }
        return new int[]{same, lack};
    }

    /**
     * 方法3：交换数组元素，把每个元素放到正确的位置
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public int[] findErrorNums3(int[] nums) {
        if (nums == null || nums.length <= 1) return null;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }
}
