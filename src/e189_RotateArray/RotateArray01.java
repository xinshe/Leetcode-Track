package e189_RotateArray;

public class RotateArray01 {

    /**
     * time complexity：O(n)
     * space complexity：O(n)
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        if (k % nums.length == 0) return;
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }
}
