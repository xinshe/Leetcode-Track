package m229_MajorityElementII;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int cand1 = nums[0], cand2 = nums[0];
        int count1 = 0, count2 = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (cand1 == nums[i])
                count1++;
            else if (cand2 == nums[i])
                count2++;
            else if (count1 == 0) {
                cand1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }

        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (cand1 == nums[i]) count1++;
            else if (cand2 == nums[i]) count2++;
        }
        if (count1 > len / 3) res.add(cand1);
        if (count2 > len / 3) res.add(cand2);
        return res;
    }
}
