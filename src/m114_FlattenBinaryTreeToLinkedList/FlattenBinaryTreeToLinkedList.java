package m114_FlattenBinaryTreeToLinkedList;

import helper.TreeNode;

/**
 * 114. 二叉树展开为链表
 *
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class FlattenBinaryTreeToLinkedList {

    // 参考：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/

    /**
     * 解法1：
     *
     * 可以发现展开的顺序其实就是二叉树的先序遍历。
     *      1. 将左子树插入到右子树的地方
     *      2. 将原来的右子树接到左子树的最右边节点
     *      3. 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    /**
     * 解法2：
     *
     */
    private TreeNode pre = null;

    public void flatten02(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        pre = root;
        root.left = null;
    }
}
