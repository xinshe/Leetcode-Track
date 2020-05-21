package e633_SumOfSquareNumbers;

/**
 * 633. 平方数之和
 *
 * 给定一个非负整数c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c。
 *
 * 示例1:
 *      输入: 5
 *      输出: True
 *      解释: 1 * 1 + 2 * 2 = 5
 *
 * 示例2:
 *      输入: 3
 *      输出: False
 *
 */
public class SumOfSquareNumbers {

    public static boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int)Math.sqrt(c);
        while (a <= b) {
            int sum = a * a + b * b;
            if (c < sum)
                b--;
            else if (c > sum)
                a++;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(4));
        System.out.println(judgeSquareSum(3));
    }
}
