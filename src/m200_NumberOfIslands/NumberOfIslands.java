package m200_NumberOfIslands;

/**
 * 200.岛屿数量
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直
 * 方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例1:
 *    输入:
 *      11110
 *      11010
 *      11000
 *      00000
 *    输出:1
 *
 * 示例2:
 *    输入:
 *      11000
 *      11000
 *      00100
 *      00011
 *    输出:3
 *
 */
public class NumberOfIslands {

    private int m, n;
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int islandNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j); // 相当于是通过dfs将连通的区域全部置为0
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') return;
        grid[r][c] = '0';
        for (int[] d : directions) {
            dfs(grid, r + d[0], c + d[1]);
        }
    }
}
