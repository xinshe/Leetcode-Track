package e461_HammingDistance;

/**
 * 461. 汉明距离
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑  ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 */
public class HammingDistance {

    /**
     * 解法1
     */
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        return Integer.bitCount(z);
    }

    /**
     * 解法2
     */
    public int hammingDistance02(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while (z != 0) {
            z &= (z - 1); // 去除z最低位的1
            cnt++;
        }
        return cnt;
    }

    public int hammingDistance03(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while (z != 0) {
            if ((z & 1) == 1) { // 从低位到高位，依次一位位地比较
                cnt++;
            }
            z = z >> 1;
        }
        return cnt;
    }
}
