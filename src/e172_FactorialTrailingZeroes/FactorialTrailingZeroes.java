package e172_FactorialTrailingZeroes;

/**
 * 172. 阶乘后的零
 *
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 *
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 */
public class FactorialTrailingZeroes {

    /**
     * 对n进行因式分解，零一定是由2和5组成，而且2的数目一定是比5多，所以只需要统计5的数目即可
     * 比如10的阶乘，从1乘到10，对这10个数全部进行因式分解，只有5和10能分解出5，即2个5，而分解出的2的数目多得多，
     * 所以只需要统计5的数目就可以了。
     */
    public int trailingZeroes(int n) {
        return (n == 0) ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
