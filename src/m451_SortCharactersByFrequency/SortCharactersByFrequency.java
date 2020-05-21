package m451_SortCharactersByFrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 451. 根据字符出现频率排序
 *
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 */
public class SortCharactersByFrequency {

    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch , map.getOrDefault(ch ,0) + 1);
        }
        List<Character>[] lists = new ArrayList[s.length()+1];
        for (char key : map.keySet()) {
            int count = map.get(key);
            if (lists[count] == null) {
                lists[count] = new ArrayList<>();
            }
            lists[count].add(key);
        }
        StringBuilder str = new StringBuilder();
        for (int i = lists.length-1; i > 0; i--) {
            if (lists[i] == null)
                continue;
            for (char ch : lists[i]) {
                for (int j = 0; j < i; j++)
                    str.append(ch);
            }
        }
        return new String(str);
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabb"));

    }
}
