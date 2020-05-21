package m1091_ShortestPathInBinaryMatrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. 二进制矩阵中的最短路径
 *
 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 *
 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 *      相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 *      C_1 位于 (0, 0)（即，值为 grid[0][0]）
 *      C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 *      如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 *
 * 提示：
 *      1 <= grid.length == grid[0].length <= 100
 *      grid[i][j] 为 0 或 1
 */
public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;

        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        grid[0][0] = 1; // 标记

        int len = queue.size();
        int pathLen = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            if (curRow == m - 1 && curCol == n - 1) return pathLen;

            for (int[] d : directions) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if (nextRow < m && nextRow >= 0 && nextCol < n && nextCol >= 0 && grid[nextRow][nextCol] == 0) {
                    queue.add(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = 1;
                }
            }
            count++;
            if (count == len) {
                pathLen++;
                count = 0;
                len = queue.size();
            }
        }
        return -1;
    }
}
