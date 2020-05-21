package m98_ValidateBinarySearchTree;

import helper.TreeNode;

import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *      节点的左子树只包含小于当前节点的数。
 *      节点的右子树只包含大于当前节点的数。
 *      所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class ValidateBinarySearchTree {

    /**
     * 解法1：递归
     *
     * 复杂度分析
     *      时间复杂度 : O(N)。每个结点访问一次。
     *      空间复杂度 : O(N)。我们跟进了整棵树。
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && node.val <= lower) return false;
        if (upper != null && node.val >= upper) return false;
        return helper(node.left, lower, node.val) && helper(node.right, node.val, upper);
    }

    /**
     * 解法2：中序遍历
     */
    public boolean isValidBST02(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();

            if (p.val <= pre) {  // 二叉搜索树的中序遍历序列为有序序列
                return false;
            }
            pre = p.val;

            p = p.right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(0 <= Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Long.MIN_VALUE);
    }
}
