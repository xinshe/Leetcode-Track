package m347_TopKFrequentElements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字节data广告系统提前批面试题
 * 这道题目是LeetCode 347题前K个高频元素 的变形题
 *
 * 给定String类型的数组 strArr，再给定整数k，请严格按照出现顺序打印出现次数前k名的字符串。
 * strArr=["1","3","3","4","1","5","1"], k=3
 * No.1 : 1, times : 3
 * No.2 : 3, times : 2
 * No.3 : 4, times : 1
 *
 * 要求：如果strArr长度为N，时间复杂度请达到O(Nlogk)
 */
public class TopKFrequentAndOrderElements {

    public List<String> topKFrequentAndOrder(String[] strArr, int k) {
        int len = strArr.length;
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(strArr[i], map.getOrDefault(strArr[i], 0) + 1);
        }
        List<String>[] buckets = new ArrayList[len + 1];
        for (String key : map.keySet()) {
            int count = map.get(key);
            if (buckets[count] == null) buckets[count] = new ArrayList<>();
            buckets[count].add(key);
        }
        List<String> result = new ArrayList<>(k);
        for (int i = len; i > 0 && result.size() < k; i--) {
            if (buckets[i] == null) continue;
            if (buckets[i].size() + result.size() > k) {
                result.addAll(buckets[i].subList(0, k - result.size()));
            } else {
                result.addAll(buckets[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strArr = {"3", "4", "3","3","4","1","5","1"};
        System.out.println(new TopKFrequentAndOrderElements().topKFrequentAndOrder(strArr, 3));
    }
}
