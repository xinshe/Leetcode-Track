package m93_RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *      输入: "25525511135"
 *      输出: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() <= 3 || s.length() > 12) return res;
        backtrack(new StringBuilder(), 0, s, res);
        return res;
    }

    private void backtrack(StringBuilder track, int count ,String s, List<String> res) {
        if (count == 4 || 0 == s.length()) {
            if (count == 4 && 0 == s.length()) {
                res.add(track.toString());
            }
            return;
        }
        for (int i = 0; i <= 2 && i < s.length(); i++) {
            if (i != 0 && s.charAt(0) == '0') { // 对于每一part，如果是一位数，那么他可以为0；如果是两位数或者三位数，那么他的第一位不能为0
                break;
            }
            String part = s.substring(0, i + 1);
            if (Integer.valueOf(part) <= 255) {
                if (track.length() != 0) { // 不是第一part
                    part = "." + part;
                }
                track.append(part);
                backtrack(track, count + 1, s.substring(i + 1), res); // 这里用的是截断了的s
                track.delete(track.length() - part.length(), track.length()); // 删除上一个加入的part
            }
        }
    }
}
