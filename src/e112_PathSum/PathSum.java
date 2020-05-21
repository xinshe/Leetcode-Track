package e112_PathSum;

import helper.TreeNode;

import java.util.Stack;

/**
 * 112. 路径总和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 题目链接：https://leetcode-cn.com/problems/path-sum/
 */

public class PathSum {

    /**
     * 方法1：递归
     *
     * 时间复杂度：我们访问每个节点一次，时间复杂度为 O(N)，其中 N 是节点个数。
     * 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，递归会调用 N 次（树的高度），
     * 因此栈的空间开销是 O(N) 。但在最好情况下，树是完全平衡的，高度只有 log(N)，因此在这种情况下空间复杂度只有
     * O(log(N)) 。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /**
     * 方法2：迭代
     *
     * 我们可以用栈将递归转成迭代的形式。深度优先搜索在除了最坏情况下都比广度优先搜索更快。最坏情况是指满足目标和的
     * root->leaf 路径是最后被考虑的，这种情况下深度优先搜索和广度优先搜索代价是相同的。
     *
     * 利用深度优先策略访问每个节点，同时更新剩余的目标和。
     * 所以我们从包含根节点的栈开始模拟，剩余目标和为 sum - root.val。
     * 然后开始迭代：弹出当前元素，如果当前剩余目标和为 0 并且在叶子节点上返回 True；如果剩余和不为零并且还处在非叶子节
     * 点上，将当前节点的所有孩子以及对应的剩余和压入栈中。
     *
     * 时间复杂度：和递归方法相同是 O(N)。
     * 空间复杂度：当树不平衡的最坏情况下是 O(N) 。在最好情况（树是平衡的）下是 O(logN)。
     *
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        nodeStack.push(root);
        sumStack.push(sum - root.val);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            int curSum = sumStack.pop();

            if (curNode.left == null && curNode.right == null && curSum == 0) return true;

            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curSum - curNode.left.val);
            }

            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curSum - curNode.right.val);
            }
        }
        return false;
    }

}