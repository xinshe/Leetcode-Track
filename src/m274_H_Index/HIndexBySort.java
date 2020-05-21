package m274_H_Index;

import java.util.Arrays;

/**
 * 复杂度:
 * 时间 O(NlogN) 空间 O(1)
 *
 * 思路:
 * 先将数组排序，我们就可以知道对于某个引用数，有多少文献的引用数大于这个数。对于引用数citations[i]，大于该引用数文献的数量是citations.length - i，
 * 而当前的H-Index则是Math.min(citations[i], citations.length - i)，我们将这个当前的H指数和全局最大的H指数来比较，得到最大H指数。
 */
public class HIndexBySort {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            int curH = Math.min(citations[i], citations.length - i); // 对于每一篇文章，它的curH决定于它本身的引用数和大于等于它的引用数的文章数目
            // 例如 citations = [3,0,6,1,5] 排序后 [0,1,3,5,6] 当i=1时, 引用数为1, 大于等于它的引用数1的文章数目为4, 那么它的当前H指数为1，如果是4的话就表示有4篇引用数大于4的文章
            // 当i=4时, 引用数为5, 大于等于它的引用数5的文章数目为2, 那么它的当前H指数为2，如果是5的话就表示有5篇引用数大于5的文章
            if (curH > h) h = curH;
        }
        return h;
    }

    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        Arrays.sort(citations);
        int count = 0;
        int n = citations.length;
        for (int c : citations) {
            if (c >= n - count) return n - count;
            else count++;
        }
        return 0;
    }
}
