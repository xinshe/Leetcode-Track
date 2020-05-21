package e404_SumOfLeftLeaves;

import helper.TreeNode;

/**
 * 404. 左叶子之和
 *
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * 题目链接：https://leetcode-cn.com/problems/sum-of-left-leaves/
 */

public class SumOfLeftLeaves {

    /**
     * 解法1：
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null) // root.left为叶子节点
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }


    /**
     * 解法2：利用递归遍历的思想
     */
    private int res = 0;

    public int sumOfLeftLeaves02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        tra(root);
        return res;
    }

    private void tra(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            if (node.left.left == null && node.left.right == null) {
                res += node.left.val;
            }
            tra(node.left);
        }
        if (node.right != null) {
            tra(node.right);
        }
    }
}
