package m144_BinaryTreePreorderTraversal;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,2,3]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

public class BinaryTreePreorderTraversal {

    /**
     * 迭代：
     * 时间复杂度：访问每个节点恰好一次，时间复杂度为 O(N) ，其中 N 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况存储整棵树，因此空间复杂度是 O(N)。
     *
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curNode;
        while (!stack.isEmpty()) {
           curNode = stack.pop();
           if (curNode == null) continue;
           res.add(curNode.val);
           stack.push(curNode.right); // 先右后左，保证左子树先遍历
           stack.push(curNode.left);
        }
        return res;
    }
}
