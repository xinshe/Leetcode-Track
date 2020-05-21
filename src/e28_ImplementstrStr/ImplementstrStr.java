package e28_ImplementstrStr;

/**
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * Clarification:
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's 
 * strstr() and Java's indexOf().
 *
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回 -1。
 *
 * 说明:
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回0 。这与C语言的strstr()以及Java的indexOf()定义相符。
 *
 */

public class ImplementstrStr {

    /**
     * 方法1：调用库函数indexOf()
     */

    /**
     * 方法2：暴力解法
     * 依次比较，就如下面这种解法
     * 或者自己不写取子串的逻辑，直接用subString()函数依次取原串的子串，然后与待比较的串比较
     */
    public int strStr(String haystack, String needle) {
        int hayLen, needleLen;
        if (needle == null || (needleLen = needle.length()) == 0) return 0;
        if (haystack == null || (hayLen = haystack.length()) == 0) return -1;
        if (hayLen < needleLen) return -1;

        int p, q;
        boolean flag;
        for (int i = 0; i < hayLen - needleLen + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                p = 1;
                q = i + 1;
                flag = true;
                while (q < hayLen && p < needleLen) {
                    if (haystack.charAt(q++) != needle.charAt(p++)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return i;
            }
        }
        return -1;
    }

    /**
     * 方法3：KMP算法
     *
     * 参考：
     * https://www.bilibili.com/video/av49930100?from=search&seid=4904074561120413241
     */
}
