package h1067_DigitCountInRange;

/**
 * 1067. 范围内的数字计数
 *
 * Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds,
 * respectively. Return the number of times that d occurs as a digit in all integers between low and high,
 * including the bounds low and high.
 *
 * Example 1:
 * Input: d = 1, low = 1, high = 13
 * Output: 6
 * Explanation:
 * The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
 *
 * Example 2:
 * Input: d = 3, low = 100, high = 250
 * Output: 35
 * Explanation:
 * The digit d=3 occurs 35 times in 103,113,123,130,131,…,238,239,243.
 *
 * Note:
 * 0 <= d <= 9
 * 1 <= low <= high <= 2×10^8
 */
public class DigitCountInRange {

    /**
     * 以 d = 3, low = 100, high = 250 来分析：
     *      个位上出现3: 103，113，...，243
     *          可以这样计算：(250 - 100) / 10 = 15    个位上一共出现15个3
     *      十位上出现3：13x，23x，其中 x 属于 [0,9]
     *          可以这样计算：((250/10 - 100/10) / 10) * 10 = 20   个位上一共出现20个3
     */
    public int digitsCount(int d, int low, int high) {
        return countDigit(d, high) - countDigit(d, low - 1);
    }

    // 正确
    int countDigit(int d, int n) {
        if(n < 0 || n < d) {
            return 0;
        }

        int count = 0;
        for(long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            count += (n / divider) * i;

            if (d > 0) {
                // comment1: tailing number need to be large than d *  i to qualify.
                count += Math.min(Math.max(n % divider - d * i + 1, 0), i);
            } else {
                if(n / divider > 0) {
                    if(i > 1) {  // comment2: when d == 0, we need avoid to take numbers like 0xxxx into account.
                        count -= i;
                        count += Math.min(n % divider + 1, i);
                    }
                }
            }
        }

        return count;
    }

    // 不正确，不清楚原因
    private int count(int d, int n) {
        // 递归退出条件
        if (n <= 9) {
            if (d == 0) return 0; // n不包括0，d包括0
            else return 1;
        }

        int cnt = 0;
        // 个位数字上 d出现的次数，不包含最后一次，e.g. N=796，
        // 首先计算1 - 789之间个位数字上 出现d的次数
        cnt += (d == 0 ? (n / 10 - 1) : n / 10); // 因为第一轮1-9中没有0，当d为0时，所以n/10要减去1

        // 然后计算790 - 796之间个位数字上 出现d的次数
        cnt += (n % 10 >= d ? 1 : 0);

        // 接着，计算除了个位数字以外其他位数上 d出现的个数, e.g. N=796，我们计算 1 - 799 之间非个位数字出现d的次数
        cnt += count(d, n / 10) * 10;

        // 最后，前面默认最后一位到9，因此我们要减去最后一位不到9的情况，e.g. N=796, 我们计算797 - 799两数非个位数字上出现d的次数
        cnt -= contains(d, n / 10) * (9 - n % 10);

        return cnt;
    }

    private int contains(int d, int n) {
        while (n > 0) {
            if (n % 10 == d) {
                return 1;
            }
            n /= 10;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new DigitCountInRange().digitsCount(3, 100, 250));
        System.out.println(new DigitCountInRange().digitsCount(1, 1, 13));
    }

}
