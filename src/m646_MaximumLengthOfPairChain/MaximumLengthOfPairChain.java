package m646_MaximumLengthOfPairChain;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 646. 最长数对链
 *
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例:
 *      输入: [[1,2], [2,3], [3,4]]
 *      输出: 2
 *      解释: 最长的数对链是 [1,2] -> [3,4]
 * 注意：
 *      给出数对的个数在 [1, 1000] 范围内。
 *
 */
public class MaximumLengthOfPairChain {

    /**
     * 解法1：DP
     *
     * 首先根据数对的第一个数排序所有的数对。
     * dp[i] 存储以 pairs[i] 结尾的最长链的长度。当 i < j 且 pairs[i][1] < pairs[j][0] 时，扩展数对链，
     * 更新 dp[j] = max(dp[j], dp[i] + 1)。
     *
     * 复杂度分析：
     *      时间复杂度：O(N^2)，其中 N 是 pairs 的长度，两层循环共需要 N^2 次计算。
     *      空间复杂度：O(N)。用于排序和存储数组dp。
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0])); // Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {   // 注意：pairs 数组已经按照第一个数排好序了
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int x : dp) {
            if (x > res) res = x;
        }
        return res;

//        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 解法2：贪心解法
     *
     * 思路：
     *      使用贪心思想扩展数对链，在所有可作为下一个数对的集合中选择第二个数最小的数对添加到数对链。
     *
     * 算法：
     *      根据思路中的描述，按照数对第二个数的升序序列遍历所有数对，如果当前数对可以加入链，则加入。
     *
     * 复杂度分析：
     *      时间复杂度：O(NlogN)，其中 N 是数对的长度。排序步骤复杂度最高，其余步骤是线性复杂度。
     *      空间复杂度：O(N)。使用常数空间存储 cur 和 ans，但是排序的空间复杂度为 O(N)，这与使用的语言有关。
     *
     */
    public int findLongestChain2(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (pair[0] > cur) {
                cur = pair[1];
                ans++;
            }
        }
        return ans;
    }
}
