package h124_BinaryTreeMaximumPathSum;

import helper.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 *
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 */
public class BinaryTreeMaximumPathSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        maxSum = Math.max(maxSum, leftGain + rightGain + root.val);
        return root.val + Math.max(leftGain, rightGain);
    }
}
