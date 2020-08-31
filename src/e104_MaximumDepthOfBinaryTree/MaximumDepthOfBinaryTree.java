package e104_MaximumDepthOfBinaryTree;

import helper.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3
 *
 * 题目链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */

public class MaximumDepthOfBinaryTree {

    /**
     * 方法1：DFS （递归）
     * 时间复杂度：我们每个结点只访问一次，因此时间复杂度为 O(N)，其中 N 是结点的数量。
     * 空间复杂度：在最糟糕的情况下，树是完全不平衡的，例如每个结点只剩下左子结点，递归将会被调用 N 次（树的高度），
     * 因此保持调用栈的存储将是 O(N)。但在最好的情况下（树是完全平衡的），树的高度将是 log(N)。因此，在这种情况下的
     * 空间复杂度将是 O(log(N))。
     *
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 方法2：BFS
     * 参考：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/javasan-chong-jie-fa-xun-huan-ban-bfs-xun-huan-ban/
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return height;
    }

    /**
     * 方法3：DFS（循环）
     * 我们可以使用两个栈，一个记录节点的 nodeStack 栈，一个记录节点所在层数的 levelStack 栈，
     * nodeStack 中每个节点在 levelStack 中都会有一个对应的值，并且他们是同时出栈，同时入栈。
     *
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/di-gui-bfsdfsde-3chong-jie-jue-fang-shi-by-sdwwld/
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> levelStack = new Stack<>();
        nodeStack.push(root);
        levelStack.push(1);
        int maxLevel = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int curLevel = levelStack.pop();

            if (node.left != null) {
                nodeStack.push(node.left);
                levelStack.push(curLevel + 1);
            } else {
                maxLevel = Math.max(curLevel, maxLevel);
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                levelStack.push(curLevel + 1);
            } else {
                maxLevel = Math.max(curLevel, maxLevel);
            }
        }
        return maxLevel;
    }

}
