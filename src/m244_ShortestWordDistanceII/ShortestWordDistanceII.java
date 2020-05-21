package m244_ShortestWordDistanceII;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a follow up of Shortest Word Distance(243). The only difference is now you are given the list of words and your method will be called repeatedly many times
 * with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance
 * between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 *
 * https://www.cnblogs.com/grandyang/p/5187640.html
 *
 */
public class ShortestWordDistanceII {

    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public ShortestWordDistanceII(ArrayList<String> list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            map.getOrDefault(list.get(i), new ArrayList<>()).add(i);
        }
    }

    /**
     * 这道题是之前那道Shortest Word Distance的拓展，不同的是这次我们需要多次调用求最短单词距离的函数。
     * 我们用哈希表来建立每个单词和其所有出现的位置的映射，然后在找最短单词距离时，我们只需要取出该单词在哈希表中映射的位置数组进行两两比较即可，
     */
    public static int shortestDistanceII(String word1, String word2) {
        int res = Integer.MAX_VALUE;
        int len1 = map.get(word1).size();
        int len2 = map.get(word2).size();
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                res = Math.min(res, Math.abs(map.get(word1).get(i) - map.get(word2).get(j)));
            }
        }
        return res;
    }

    /**
     * 我们可以优化上述的代码，使查询的复杂度由上面的O(MN)变为O(M+N)，其中M和N为两个单词的数目，我们需要两个指针i和j来指向位置数组中的某个位置，开始初始化都为0，
     * 然后比较位置数组中的数字，将较小的一个的指针向后移动一位，直至其中一个数组遍历完成即可。
     */
    public static int shortestDistanceII2(String word1, String word2) {
        int i = 0;
        int j = 0;
        int res = Integer.MAX_VALUE;
        while (i < map.get(word1).size() && j < map.get(word2).size()) {
            res = Math.min(res, Math.abs(map.get(word1).get(i) - map.get(word2).get(j)));
            if (map.get(word1).get(i) < map.get(word2).get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
}
