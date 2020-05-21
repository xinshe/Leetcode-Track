package e119_PascalsTriangleII;

import java.util.Arrays;
import java.util.List;

public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        Integer[] res = new Integer[rowIndex + 1];  // rowIndex 从0开始，所以对应到数组长度要+1
        for (int i = 0; i <= rowIndex; i++) {   // i为层数，从第0层开始到第rowIndex层
            // 每一层从后往前开始计算
            res[i] = 1;  // 每一层的最后一个元素为1
            for (int j = i - 1; j >= 1; j--) {
                res[j] = res[j] + res[j - 1];   // 计算中间元素，从i-1（倒数第2个）到1（第2个）
            }
            res[0] = 1;   // 每一层的第一个元素为1
        }
        return Arrays.asList(res);
    }
}
