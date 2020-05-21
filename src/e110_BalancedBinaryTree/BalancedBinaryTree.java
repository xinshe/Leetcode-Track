package e110_BalancedBinaryTree;

import helper.TreeNode;

/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 */

public class BalancedBinaryTree {

    /**
     * 方法1：从底至顶（提前阻断法）
     * 对二叉树做深度优先遍历DFS，递归过程中：
     *      终止条件：当DFS越过叶子节点时，返回高度0；
     *      返回值：
     *          从底至顶，返回以每个节点root为根节点的子树最大高度(左右子树中最大的高度值加1  max(left,right) + 1)；
     *          当我们发现有一例 左/右子树高度差 ＞ 1 的情况时，代表此树不是平衡树，返回-1；
     *      当发现不是平衡树时，后面的高度计算都没有意义了，因此一路返回-1，避免后续多余计算。
     * 最差情况是对树做一遍完整DFS，时间复杂度为 O(N)
     *
     */
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        if (-1 == left) return -1;
        int right = depth(root.right);
        if (-1 == right) return -1;
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 下面是此类问题的一个通用写法。
     */
    private boolean result;

    private int depth2(TreeNode root) {
        if (root == null) {  return 0;} // 递归退出条件
        int left = depth2(root.left); // root左子树高度
        int right = depth2(root.right); // root右子树高度
        if (Math.abs(left - right) > 1) { result = false;}  // 针对具体问题处理的逻辑放在此处
        return Math.max(left, right) + 1; // 当前子树高度+1
    }

    /**
     * 解法2：更容易理解
     */
    public boolean isBalanced02(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
