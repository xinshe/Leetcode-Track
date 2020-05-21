package e27_RemoveElement;

public class RemoveElement01 {

    /**
     * [0,1,2,2,3,0,4,2]
     *      r
     *          i
     */
    public int removeElement(int[] nums, int val) {
        if (null == nums || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        return res;
    }

}
