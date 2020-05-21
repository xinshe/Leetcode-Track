package m89_GrayCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 *      输入: 2
 *      输出: [0,1,3,2]
 *      解释:
 *          00 - 0
 *          01 - 1
 *          11 - 3
 *          10 - 2
 *
 *      对于给定的 n，其格雷编码序列并不唯一。
 *      例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *          00 - 0
 *          10 - 2
 *          11 - 3
 *          01 - 1
 *
 * 示例 2:
 *      输入: 0
 *      输出: [0]
 *      解释: 我们定义格雷编码序列必须以 0 开头。
 *          给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *          因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 * 参考：https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
 */
public class GrayCode {

    /**
     * 解法1：DP
     *
     * 如果知道了 n = 2 的解的话，如果是 { 0, 1, 3, 2}，那么 n = 3 的解就是 { 0, 1, 3, 2, 2 + 4, 3 + 4, 1 + 4, 0 + 4 }，
     * 即 { 0 1 3 2 6 7 5 4 }。之前的解直接照搬过来，然后倒序把每个数加上 1 << ( n - 1) 添加到结果中即可。
     *
     * 时间复杂度：O(2^n)，因为有这么多的结果。
     * 空间复杂度：O（1）。
     *
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int increase = 1 << i;
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(res.get(j) + increase);
            }
        }
        return res;
    }
}
