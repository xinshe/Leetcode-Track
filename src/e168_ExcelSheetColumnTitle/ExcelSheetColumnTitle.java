package e168_ExcelSheetColumnTitle;

/**
 * 168. Excel表列名称（26进制）
 *
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 *
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 *
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 *
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 *
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        n--; // 因为是从 1 开始计算的，而不是从 0 开始，因此需要对 n 执行 -1 操作。
        return convertToTitle(n / 26) + (char)(n % 26 + 'A');
    }
}
