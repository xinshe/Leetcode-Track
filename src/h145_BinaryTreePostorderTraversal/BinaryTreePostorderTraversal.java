package h145_BinaryTreePostorderTraversal;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的后序遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [3,2,1]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

public class BinaryTreePostorderTraversal {

    /**
     * 迭代
     *
     * 前序遍历为 root -> left -> right，后序遍历为 left -> right -> root。
     * 可以修改前序遍历成为 root -> right -> left，那么这个顺序就和后序遍历正好相反。
     *
     * 时间复杂度：访问每个节点恰好一次，因此时间复杂度为 O(N)，其中 N 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况需要保存整棵树，因此空间复杂度为 O(N)。
     *
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>(); // 这里还可以用LinkedList来实现，那么在ret添加节点时，调用的是addFirst()方法
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curNode;
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            if (curNode == null) continue;
            ret.add(curNode.val); // 如果ret的类型是LinkedList，那么这里是 ret.addFirst(curNode.val)
            stack.push(curNode.left);
            stack.push(curNode.right);
        }
        Collections.reverse(ret);
        return ret;
    }
}
