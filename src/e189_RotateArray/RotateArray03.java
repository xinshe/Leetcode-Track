package e189_RotateArray;

public class RotateArray03 {

    /**
     * time complexity：O(n)
     * space complexity：O(1)
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        k %= nums.length;
        if (k == 0) return;
        int count = 0;  // 移动的次数（移动数组长度的次数一定可以把所有元素放到正确位置）
        for (int i = 0; count < nums.length; i++) { // i为每轮移动的开始位置
            int curIndex = i;
            int cur = nums[curIndex];
            do {
                int nextIndex = (curIndex + k) % nums.length;
                int next = nums[nextIndex];
                nums[nextIndex] = cur;
                cur = next;
                curIndex = nextIndex;
                count++;
            } while (curIndex != i);  // 移动了原来的开始位置，即形成了一个闭环，则重新开始
        }
    }
}
