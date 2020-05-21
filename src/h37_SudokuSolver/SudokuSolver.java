package h37_SudokuSolver;

/**
 * 37. 解数独
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *      数字 1-9 在每一行只能出现一次。
 *      数字 1-9 在每一列只能出现一次。
 *      数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *      空白格用 '.' 表示。
 *
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.'。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9 x 9 形式的。
 *
 */
public class SudokuSolver {

    private boolean[][] rowUsed = new boolean[9][10];
    private boolean[][] colUsed = new boolean[9][10];
    private boolean[][] cubeUsed = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rowUsed[i][num] = true;
                colUsed[j][num] = true;
                cubeUsed[cubeNum(i, j)][num] = true;
            }
        }
        backtrack(0, 0, board);
    }

    private boolean backtrack(int row, int col, char[][] board) {
        while (row < 9 && board[row][col] != '.') {
            row = (col == 8) ? row + 1 : row;
            col = (col == 8) ? 0 : col + 1;
        }

        if (row == 9) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (rowUsed[row][num] || colUsed[col][num] || cubeUsed[cubeNum(row, col)][num]) {
                continue;
            }

            rowUsed[row][num] = colUsed[col][num] = cubeUsed[cubeNum(row, col)][num] = true;
            board[row][col] = (char) (num + '0'); // 这里必须要加0
            if (backtrack(row, col, board)) {
                return true;
            }
            board[row][col] = '.';
            rowUsed[row][num] = colUsed[col][num] = cubeUsed[cubeNum(row, col)][num] = false;
        }
        return false;
    }

    private int cubeNum(int row, int col) {
        return (row / 3) * 3 + col / 3;
    }
}
