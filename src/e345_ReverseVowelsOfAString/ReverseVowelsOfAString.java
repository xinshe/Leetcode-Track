package e345_ReverseVowelsOfAString;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 345. 反转字符串中的元音字母
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 * 输入: "hello"
 * 输出: "holle"
 *
 * 示例 2:
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 *
 */
public class ReverseVowelsOfAString {

    private final static HashSet<Character> vowels = new
            HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int low = 0;
        int high = s.length()-1;
        while (low < high) {
            while (low < high && !vowels.contains(ch[low]))
                low++;
            while (low < high && !vowels.contains(ch[high]))
                high--;
            if (low < high) {
                char tmp = ch[low];
                ch[low] = ch[high];
                ch[high] = tmp;
                low++;
                high--;
            }
        }
        return new String(ch);
    }

    /**
     * 推荐此解法
     */
    public static String reverseVowels02(String s) {
        char[] ch = s.toCharArray();
        int low = 0;
        int high = s.length()-1;
        while (low < high) {
            char chLow = ch[low];
            char chHigh = ch[high];
            if (!vowels.contains(chLow))
                low++;
            else if (!vowels.contains(chHigh))
                high--;
            else {
                ch[low++] = chHigh;
                ch[high--] = chLow;
            }
        }
        return new String(ch);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels02("hello"));
        System.out.println(reverseVowels02("leetcode"));
        System.out.println(reverseVowels02(".,"));
    }
}
