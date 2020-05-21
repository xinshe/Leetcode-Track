package e687_LongestUnivaluePath;

import helper.TreeNode;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 * 输入:
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 * 2
 *
 * 示例 2:
 * 输入:
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 * 2
 *
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 */

public class LongestUnivaluePath {

    /**
     * 方法1：递归
     *
     * 时间复杂度：O(N)，其中 N 是树中节点数。我们处理每个节点一次。
     * 空间复杂度：O(H)，其中 H 是树的高度。我们的递归调用栈可以达到 H 层的深度。
     *
     */
    private int path = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left); // 得到以当前节点所在的这条路径的长度
        int right = dfs(root.right);

        int leftPath = (root.left != null && root.val == root.left.val) ? left + 1 : 0; // 更新长度值
        int rightPath = (root.right != null && root.val == root.right.val) ? right + 1 : 0;

        path = Math.max(path, leftPath + rightPath); // 题中所指的路径长是由左右两边的路径长度和组成（如示例 2）
        return Math.max(leftPath, rightPath);
    }
}
