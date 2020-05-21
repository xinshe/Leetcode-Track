package m274_H_Index;

/**
 * 复杂度
 * 时间 O(N) 空间 O(N)
 *
 * 思路
 * 也可以不对数组排序，我们额外使用一个大小为N+1的数组bucket。bucket[i]表示有多少文章被引用了i次，这里如果一篇文章引用大于N次，我们就将其当为N次，
 * 因为H指数不会超过文章的总数N。为了构建这个数组，我们需要先将整个文献引用数组遍历一遍，对相应的格子加一。统计完后，我们从N向1开始遍历这个统计数组。
 * 如果遍历到某一个引用次数时，大于或等于该引用次数的文章数量，大于引用次数本身时，我们可以认为这是H指数。之所以不用再向下找，因为我们要取最大的H指数。
 * 那如何求大于或等于某个引用次数的文章数量呢？我们可以用一个变量，从高引用次的文章数累加下来。因为我们知道，如果有x篇文章的引用大于等于3次，
 * 那引用大于等于2次的文章数量一定是x加上引用次数等于2次的文章数量。
 *
 * https://segmentfault.com/a/1190000003794831
 */
public class HIndexByBucket {

    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        int n = citations.length;
        int[] bucket = new int[n+1]; // bucket[i]表示引用数为i的文章数目，引用数大于等于n的都放入bucket[n].
        // 为什么数组大小为n+1就可以了呢？因为h-index就是有h篇文章的引用大于h，而文章的总数目为n，所以h≤n.
        for (int c : citations) {
            if (c >= n)
                bucket[n]++;
            else
                bucket[c]++;
        }

        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            if (count >= i)
                return i;
        }
        return 0;

    }
}
