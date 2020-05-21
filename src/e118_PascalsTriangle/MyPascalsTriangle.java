package e118_PascalsTriangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res; // 当numRows为0时，直接返回
        res.add(Arrays.asList(1));  // 当numRows为1时
        for (int i = 1; i < numRows; i++) { // 当numRows>2时，才能进入这个循环
            List<Integer> cur = new ArrayList<>();
            List<Integer> pre = res.get(i - 1);
            cur.add(1);      // 第0个位置 为1
            for (int j = 1; j < i; j++) {   // 处理中间第1至i-1个元素，每一层的长度为 i+1
                cur.add(pre.get(j) + pre.get(j - 1));
            }
            cur.add(1);     // 最后一个位置 为1
            res.add(cur);
        }
        return res;
    }
}
