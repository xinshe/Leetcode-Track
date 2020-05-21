package m347_TopKFrequentElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *      输入: nums = [1,1,1,2,2,3], k = 2
 *      输出: [1,2]
 *
 * 示例 2:
 *      输入: nums = [1], k = 1
 *      输出: [1]
 *
 * 说明：
 *      你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 *      你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 */
public class TopKFrequentElements {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] list = new ArrayList[nums.length+1];
        for (int key : map.keySet()) {
            int count = map.get(key);
            if (list[count] == null) {
                list[count] = new ArrayList<>();
            }
            list[count].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = list.length-1; i > 0 && k > 0; i--) {
            if (list[i] != null) {
                for (int num : list[i]) {
                    if (k-- > 0)
                        res.add(num);
                    else
                        break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(topKFrequent(nums, 1));
    }
}
