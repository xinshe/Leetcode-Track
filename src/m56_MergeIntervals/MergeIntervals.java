package m56_MergeIntervals;

import javafx.util.Pair;

import java.util.*;

/**
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 题目链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (i + 1 < intervals.length && intervals[i + 1][0] <= end) {
                if (intervals[i + 1][1] > end) {
                    end = intervals[i + 1][1];
                }
                i++;
            }
            list.add(new Pair<>(start, end));
        }
        int len = list.size();
        int[][] res = new int[len][2];
        for (int i = 0; i < len; i++) {
            res[i][0] = list.get(i).getKey();
            res[i][1] = list.get(i).getValue();
        }

        return res;
    }

    /**
     * 换一个写法
     */
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][0];
        }
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        map.put(start, end);
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= end) { // 有重叠
                if (intervals[i][1] > end) {
                    end = intervals[i][1];
                }
            } else { // 无重叠
                start = intervals[i][0];
                end = intervals[i][1];
            }
            map.put(start, end);
        }
        int[][] ret = new int[map.size()][2];
        int index = 0;
        for (int key : map.keySet()) {
            ret[index][0] = key;
            ret[index][1] = map.get(key);
            index++;
        }
        return ret;
    }
}
