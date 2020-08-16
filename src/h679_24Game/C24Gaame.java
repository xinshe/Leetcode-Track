package h679_24Game;

import java.util.ArrayList;
import java.util.List;

/**
 * 679. 24 点游戏
 *
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 *
 * 示例 1:
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 *
 * 示例 2:
 * 输入: [1, 2, 1, 2]
 * 输出: False
 *
 * 注意:
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 *
 * 题目链接：https://leetcode-cn.com/problems/24-game
 */
public class C24Gaame {

    // 参考：https://leetcode-cn.com/problems/24-game/solution/24dian-you-xi-by-leetcode/
    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        List<Double> list = new ArrayList<>();
        for (int num : nums) list.add((double) num);
        return slove(list);
    }

    private boolean slove(List<Double> list) {
        if (list.size() == 0) return false;
        if (list.size() == 1) return Math.abs(list.get(0) - 24) < 1e-6;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    List<Double> tmp = new ArrayList<>();

                    for (int k = 0; k < list.size(); k++) {
                        if (k != i && k != j) {
                            tmp.add(list.get(k));
                        }
                    }

                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && j > i) continue;
                        if (k == 0) tmp.add(list.get(i) + list.get(j));
                        if (k == 1) tmp.add(list.get(i) * list.get(j));
                        if (k == 2) tmp.add(list.get(i) - list.get(j));
                        if (k == 3) {
                            if (list.get(j) != 0) {
                                tmp.add(list.get(i) / list.get(j));
                            } else {
                                continue;
                            }
                        }
                        if (slove(tmp)) return true;
                        tmp.remove(tmp.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
