package e504_Base7;

/**
 * 504. 七进制数
 *
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 *
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 * 注意: 输入范围是 [-1e7, 1e7] 。
 *
 * 题目链接：https://leetcode-cn.com/problems/base-7
 */
public class Base7 {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isNegative = num < 0;
        if (isNegative) {
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        String ret = sb.reverse().toString();
        return isNegative ? "-" + ret : ret;
    }

    // Java 中 static String toString(int num, int radix) 可以将一个整数转换为 radix 进制表示的字符串。
    public String convertToBase702(int num) {
        return Integer.toString(num, 7);
    }
}
