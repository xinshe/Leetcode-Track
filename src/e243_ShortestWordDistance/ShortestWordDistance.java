package e243_ShortestWordDistance;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
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
 * https://www.cnblogs.com/grandyang/p/5187041.html
 */

public class ShortestWordDistance {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("practice", "makes", "perfect", "coding", "makes"));
        System.out.println(shortestDistance(words, "coding", "practice"));
        System.out.println(shortestDistance(words, "makes", "coding"));

        System.out.println(shortestDistance2(words, "coding", "practice"));
        System.out.println(shortestDistance2(words, "makes", "coding"));
    }

    /**
     * 用两个变量idx1,idx2初始化为-1，然后我们遍历数组，遇到单词1，就将其位置存在idx1里，若遇到单词2，就将其位置存在idx2里，如果此时idx1, idx2都不为-1了，那么我们更新结果
     */
    public static int shortestDistance(ArrayList<String> words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int len = words.size();
        int res = len;
        for (int i = 0; i < len; i++) {
            if (word1.equals(words.get(i))) {
                idx1 = i;
            } else if (word2.equals(words.get(i))) {
                idx2 = i;
            }
            if (idx1 != -1 && idx2 != -1) res = Math.min(res, Math.abs(idx1 - idx2));
        }
        return res;
    }

    /**
     * 下面这种方法只用一个辅助变量idx，初始化为-1，然后遍历数组，如果遇到等于两个单词中的任意一个的单词，我们在看idx是否为-1，若不为-1，且指向的单词和当前遍历到的单词不同，就更新结果
     */
    public static int shortestDistance2(ArrayList<String> words, String word1, String word2) {
        int idx = -1;
        int len = words.size();
        int res = len;
        for (int i = 0; i < len; i++) {
            if (words.get(i).equals(word1) || words.get(i).equals(word2)) {
                if (idx != -1 && !words.get(i).equals(words.get(idx))) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }
}
