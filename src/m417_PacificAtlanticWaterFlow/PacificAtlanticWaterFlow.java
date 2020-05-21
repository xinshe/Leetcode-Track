package m417_PacificAtlanticWaterFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 *
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于
 * 大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 * 提示：
 *      输出坐标的顺序不重要
 *      m 和 n 都小于150
 *
 * 示例：
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 */
public class PacificAtlanticWaterFlow {

    private int m, n;
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ret;
        m = matrix.length;
        n = matrix[0].length;

        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, canReachP, i, 0);
            dfs(matrix, canReachA, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, canReachP, 0, j);
            dfs(matrix, canReachA, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachP[i][j] && canReachA[i][j]) {
                    ret.add(Arrays.asList(i, j));
                }
            }
        }
        return ret;
    }

    private void dfs(int[][] matrix, boolean[][] canReach, int r, int c) {
        if (canReach[r][c]) return;
        canReach[r][c] = true;
        for (int[] d : directions) {
            int nextR = r + d[0];
            int nextC = c + d[1];
            if (nextR < 0 || nextR == m || nextC < 0 || nextC == n || matrix[r][c] > matrix[nextR][nextC])
                continue;
            dfs(matrix, canReach, nextR, nextC);
        }
    }

}
