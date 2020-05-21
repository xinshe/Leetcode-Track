package m513_FindBottomLeftTreeValue;

import helper.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *  
 * 示例 2:
 * 输入:
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 * 输出:
 * 7
 *  
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 *
 */

public class FindBottomLeftTreeValue {

    /**
     * 方法1：递归
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curNode = root;
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            if (curNode.right != null) queue.add(curNode.right); // 先判断右边就表示每一层是从右往左遍历的
            if (curNode.left != null) queue.add(curNode.left);
        }
        return curNode.val;
    }

    /**
     * 方法2：递归
     *
     * 中序遍历，找到最深层，将左边第一个元素保存在结果中
     */
    int res = -1;
    int maxDepth = -1;

    public int findBottomLeftValue2(TreeNode root) {
        inOrder(root, 0);
        return res;
    }

    // 前序 中序 后序都可以 只要左在右之前就行
    private void inOrder(TreeNode root, int depth) {
        if (root == null) return;
        inOrder(root.left, depth + 1);
        // 判断是否是最大深度
        if (depth > maxDepth) {
            maxDepth = depth;
            res = root.val;
        }
        inOrder(root.right, depth + 1);
    }
}
