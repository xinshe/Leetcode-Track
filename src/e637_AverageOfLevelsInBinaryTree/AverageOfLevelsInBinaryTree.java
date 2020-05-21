package e637_AverageOfLevelsInBinaryTree;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 示例 1:
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 *
 * 注意：
 * 节点值的范围在32位有符号整数范围内。
 *
 */

public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res; // 保证加进队列的节点不为空

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode curNode;
        while (!queue.isEmpty()) {
            double sum = 0;
            int cnt = queue.size();

            for (int i = 0; i < cnt; i++) {
                curNode = queue.poll();
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
                sum += curNode.val;
            }
            res.add(sum / cnt);
        }
        return res;
    }
}
