package e572_SubtreeOfAnotherTree;

import helper.TreeNode;

/**
 * 572. 另一个树的子树
 *
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和
 * 这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 */

public class SubtreeOfAnotherTree {

    /**
     * 方法1：比较节点
     *
     * 复杂度分析
     *
     * 时间复杂度：O(mn)。在最坏的情况下（倾斜树）traverse 需要 O(mn)时间。
     * 空间复杂度：O(n)，递归树的深度可以达到 n。 n 表示 s 中的节点数
     *
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        // 让t从树s的根开始比较 || 从树s的根的左节点开始比较 || 从树s的根的右节点开始比较
        return isSubtreeWithRoot(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSubtreeWithRoot(TreeNode s, TreeNode t) { // 比较树s和树t
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSubtreeWithRoot(s.left, t.left) && isSubtreeWithRoot(s.right, t.right); // 依次往下比较
    }
}

