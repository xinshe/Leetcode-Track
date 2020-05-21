package e543_DiameterOfBinaryTree;

import helper.TreeNode;

/**
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */

public class DiameterOfBinaryTree {

    /**
     * 时间复杂度：O(N)，每个节点只访问一次。
     * 空间复杂度：O(N)，深度优先搜索的栈开销。
     */
    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0; // 访问到空节点了，返回0
        }
        int left = depth(root.left); // 左儿子为根的子树的深度
        int right = depth(root.right); // 右儿子为根的子树的深度
        res = Math.max(res, left + right); // 路径长度为边的数目，即路径上的节点数目减1。路径上的节点数目为left + right + 1
        return Math.max(left, right) + 1; // 返回该节点为根的子树的深度
    }
}
