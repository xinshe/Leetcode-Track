package e111_MinimumDepthOfBinaryTree;

import helper.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度2.
 *
 * 题目链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/submissions/
 */

public class MinimumDepthOfBinaryTree {

    /**
     * 方法1：递归
     *
     * 时间复杂度：我们访问每个节点一次，时间复杂度为 O(N) ，其中 N 是节点个数。
     * 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，递归会调用 N （树的高度）次，
     * 因此栈的空间开销是 O(N) 。但在最好情况下，树是完全平衡的，高度只有 log(N)，因此在这种情况下空间复杂度只有O(log(N)) 。
     *
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 这里相比求二叉树最大深度的题（104），多了对left、right为0的情况的判断。因为如果left/right中有零值，做Math.min(left, right)的操作结果就会出错
        if (left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * 方法2：迭代（BFS思想）
     * 利用宽度优先搜索，我们按照树的层次去迭代，第一个访问到的叶子就是最小深度的节点，这样就不要遍历所有的节点了。
     *
     * 时间复杂度：最坏情况下，这是一棵平衡树，我们需要按照树的层次一层一层的访问完所有节点，除去最后一层的节点。
     * 这样访问了 N/2 个节点，因此复杂度是 O(N)。
     * 空间复杂度：和时间复杂度相同，也是 O(N)。
     *
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return res;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
}
