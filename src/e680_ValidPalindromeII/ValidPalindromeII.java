package e680_ValidPalindromeII;

/**
 * 680. 验证回文字符串Ⅱ
 *
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 *
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 */
public class ValidPalindromeII {

    /**
     * 解法1：
     * 这种方法超时了
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N)
     */
    public static boolean validPalindrome(String s) {
        int len = s.length();
        if (judge(s))
            return true;
        else
            for (int i = 0; i < len; i++) {
                String str = s.substring(0,i) + s.substring(i+1, len);
                if (judge(str))
                    return true;
            }
        return false;
    }

    private static boolean judge(String s) {
        char[] ch = s.toCharArray();
        int len = s.length();
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            if (ch[low] != ch[high])
                return false;
            low++;
            high--;
        }
        return true;
    }

    /**
     * 解法2 （推荐此解法）
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static boolean validPalindrome02(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }
        return true;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(validPalindrome02(s));
    }
}
