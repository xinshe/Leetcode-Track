package m245_ShortestWordDistanceIII;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 *
 * Note:
 * You may assume word1 and word2 are both in the list.
 *
 * https://www.cnblogs.com/grandyang/p/5192426.html
 *
 * 这道题还是让我们求最短单词距离，有了之前两道题Shortest Word Distance II和Shortest Word Distance的基础，就大大降低了题目本身的难度。
 * 这道题增加了一个条件，就是说两个单词可能会相同，
 */

public class ShortestWordDistanceIII {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("practice", "makes", "perfect", "coding", "makes"));
        System.out.println(shortestDistance(words, "coding", "practice"));
        System.out.println(shortestDistance(words, "makes", "coding"));

        System.out.println(shortestDistance(words, "coding", "coding"));
        System.out.println(shortestDistance(words, "makes", "makes"));
    }

    public static int shortestDistance(ArrayList<String> words, String word1, String word2) {
        int idx = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.size(); i++) {
            if (word1.equals(words.get(i)) || word2.equals(words.get(i))) {
                if (idx != -1 && (word1.equals(word2) || !words.get(i).equals(words.get(idx)))) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }
}
