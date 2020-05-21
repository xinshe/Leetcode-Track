package e538_ConvertBSTToGreaterTree;

import helper.TreeNode;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 */

public class ConvertBSTToGreaterTree {

    /**
     * 中序遍历解法
     *
     * 其实这里可以将一棵二叉搜索树扁平化成为一个有序数组，然后从数组右边往左边遍历，累加每个位置的元素值，并且赋给
     * 每个位置。
     * 对应到二叉搜索树中就是先遍历右子树，再遍历根，最后左子树
     */
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traver(root);
        return root;
    }

    private void traver(TreeNode node) {
        if (null == node) return;
        traver(node.right);
        sum += node.val;
        node.val = sum;
        traver(node.left);
    }

    /**
     * 思路一样的另一种写法
     */
    private int add = 0;

    public TreeNode convertBST2(TreeNode root) {
        if (null == root) return root;
        convertBST2(root.right);
        root.val += add;
        add = root.val;
        convertBST2(root.left);
        return root;
    }

}
