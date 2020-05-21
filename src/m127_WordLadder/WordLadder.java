package m127_WordLadder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 127. 单词接龙
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 转换需遵循如下规则：
 *      每次转换只能改变一个字母。
 *      转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 *      如果不存在这样的转换序列，返回 0。
 *      所有单词具有相同的长度。
 *      所有单词只由小写字母组成。
 *      字典中不存在重复的单词。
 *      你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例1:
 *      输入:
 *      beginWord = "hit",
 *      endWord = "cog",
 *      wordList = ["hot","dot","dog","lot","log","cog"]
 *      输出: 5
 *
 *      解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * 示例2:
 *      输入:
 *      beginWord = "hit"
 *      endWord = "cog"
 *      wordList = ["hot","dot","dog","lot","log"]
 *      输出: 0
 *      解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 */

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int size = wordList.size();
        int beginWordIndex = size - 1;
        int endWordIndex = -1;
        for (int i = 0; i < size; i++) {
            if (wordList.get(i).equals(endWord)) {
                endWordIndex = i;
                break;
            }
        }
        if (endWordIndex == -1) return 0;

        List<Integer>[] graph = buildGraph(wordList);
        return getShortestPath(graph, beginWordIndex, endWordIndex);
    }

    // BFS
    private int getShortestPath(List<Integer>[] graph, int start, int end) {
        boolean[] marked = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        marked[start] = true;
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (next == end) return path;
                    if (marked[next]) continue;
                    queue.add(next);
                    marked[next] = true;
                }
            }
        }
        return 0;
    }

    private List<Integer>[] buildGraph(List<String> wordList) {
        int size = wordList.size();
        List<Integer>[] graph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (isConnected(wordList.get(i), wordList.get(j))) {
                    graph[i].add(j);
                }
            }
        }
        return graph;
    }

    private boolean isConnected(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0; i < word1.length() && diffCnt <= 1; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

}
