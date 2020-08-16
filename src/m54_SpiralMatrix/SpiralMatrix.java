package m54_SpiralMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 *
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (true) {
            for (int col = left; col <= right; col++) {
                res.add(matrix[up][col]);
            }
            up++;
            if (up > down) break;

            for (int row = up; row <= down; row++) {
                res.add(matrix[row][right]);
            }
            right--;
            if (right < left) break;

            for (int col = right; col >= left; col--) {
                res.add(matrix[down][col]);
            }
            down--;
            if (down < up) break;

            for (int row = down; row >= up; row--) {
                res.add(matrix[row][left]);
            }
            left++;
            if (left > right) break;
        }
        return res;
    }
}
