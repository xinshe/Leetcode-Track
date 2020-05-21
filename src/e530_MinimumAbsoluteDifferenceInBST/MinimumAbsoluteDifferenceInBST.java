package e530_MinimumAbsoluteDifferenceInBST;

import helper.TreeNode;

/**
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 *
 * 示例 :
 * 输入:
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出:
 * 1
 *
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 * 注意: 树中至少有2个节点。
 *
 * 扩展：二叉搜索树的相关题目 可以使用中序遍历来解决的
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/solution/zhong-xu-bian-li-tuan-mie-xi-lie-er-cha-sou-suo-sh/
 */

public class MinimumAbsoluteDifferenceInBST {

    /**
     * 利用二叉查找树的中序遍历为有序的性质，计算中序遍历中临近的两个节点之差的绝对值，取最小值。
     */
    private int minDiff = Integer.MAX_VALUE;
    private int preVal = -1;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minDiff;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (preVal != -1) minDiff = Math.min(minDiff, Math.abs(root.val - preVal));
        preVal = root.val;
        inOrder(root.right);
    }
}
