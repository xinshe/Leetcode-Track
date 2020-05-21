package m763_PartitionLabels;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示
 * 每个字符串片段的长度的列表。
 *
 * 示例 1:
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 *
 */
public class PartitionLabels {

    /**
     * 解法1：贪心
     *
     * 思路：
     * 策略就是不断地选择从最左边起最小的区间。可以从第一个字母开始分析，假设第一个字母是 'a'，那么第一个区间一定包含
     * 最后一次出现的 'a'。但第一个出现的 'a' 和最后一个出现的 'a' 之间可能还有其他字母，这些字母会让区间变大。举个
     * 例子，在 "abccaddbeffe" 字符串中，第一个最小的区间是 "abccaddb"。
     * 通过以上的分析，我们可以得出一个算法：对于遇到的每一个字母，去找这个字母最后一次出现的位置，用来更新当前的最小
     * 区间。
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        int len = S.length();
        int[] last = new int[26];
        for (int i = 0; i < len; i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        int firstIndex = 0, lastIndex = 0;
        for (int i = 0; i < len; i++) {
            lastIndex = Math.max(lastIndex, last[S.charAt(i)-'a']);
            if (i == lastIndex) {
                res.add(lastIndex - firstIndex + 1);
                firstIndex = lastIndex + 1;
            }
        }
        return res;
    }
}
