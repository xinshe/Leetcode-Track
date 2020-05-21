package m230_KthSmallestElementInABST;

import helper.TreeNode;

import java.util.HashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 *
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 */

public class KthSmallestElementInABST {

    /**
     * 方法1：中序遍历
     * 根据二叉搜索树 左子树 < 根 < 右子树 的性质。容易想到用中序遍历来解决
     */
    private int val;
    private int cnt = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return val;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            val = root.val;
            return;
        }
        inOrder(root.right, k);
    }

    /**
     * 方法2：递归
     */
    public int kthSmallest2(TreeNode root, int k) {
        int leftCnt = count(root.left); // 计算根节点root的左子树的节点数
        if (leftCnt == k - 1) return root.val; // 左子树的节点数为k-1，那么第k小的数，就是根节点
        if (leftCnt > k - 1) return kthSmallest2(root.left, k);// 左子树的节点数大于k-1，那么第k小的数一定出现在左子树
        return kthSmallest2(root.right, k - leftCnt - 1); // 左子树的节点数小于k-1，那么第k小的数一定出现在右子树，减去的1减的是根节点
    }

    private int count(TreeNode node) { // 计算以node为根的树的节点数目
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }


}
