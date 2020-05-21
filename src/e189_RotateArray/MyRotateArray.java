package e189_RotateArray;

public class MyRotateArray {

    /**
     * time complexity：O((k%n)*n)
     * space complexity：O(1)
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        int n = nums.length;
        k %= n;
        if (k == 0) return;
        while (k-- > 0) {
            int flag = nums[n - 1];
            for (int i = n - 2; i >= 0 ; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = flag;
        }
    }
}
