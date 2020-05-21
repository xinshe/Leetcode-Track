package e669_TrimABinarySearchTree;

import helper.TreeNode;

/**
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 示例 1:
 * 输入:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * 输出:
 *     1
 *       \
 *        2
 *
 * 示例 2:
 * 输入:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * 输出:
 *       3
 *      /
 *    2
 *   /
 *  1
 *
 */

public class TrimABinarySearchTree {

    /**
     * 递归
     *
     * 当 node.val > R，那么修剪后的二叉树必定出现在节点的左边。
     * 类似地，当 node.val < L，那么修剪后的二叉树出现在节点的右边。否则，我们将会修剪树的两边。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是给定的树的全部节点。我们最多访问每个节点一次。
     * 空间复杂度：O(N)，即使我们没有明确使用任何额外的内存，在最糟糕的情况下，我们递归调用的栈可能与节点数一样大。
     *
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (null == root) return null;
        if (root.val > R) return trimBST(root.left, L, R);
        if (root.val < L) return trimBST(root.right, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
