package m236_LowestCommonAncestorOfABinaryTree;

import helper.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先
 * 且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * 题目链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 */

public class LowestCommonAncestorOfABinaryTree {

    /**
     * 解法1：递归
     *
     * 复杂度分析
     *      时间复杂度：(N)，N 是二叉树中的节点数，最坏情况下，我们需要访问二叉树的所有节点。
     *      空间复杂度：O(N)，这是因为递归堆栈使用的最大空间位N,斜二叉树的高度可以是 N。
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        else if (left != null) return left;
        else if (right != null) return right;
        return null;

        // 以上判断可改写为
        // return left == null ? right : right == null ? left : root;
    }

    /**
     * 解法2：保存父节点（比较通用的解法）
     */
    public TreeNode lowestCommonAncestor02(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);
        stack.push(root);
        // 先序遍历
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
                parents.put(cur.left, cur);
            }
            if (cur.right != null) {
                stack.push(cur.right);
                parents.put(cur.right, cur);
            }
        }

        HashSet<TreeNode> path = new HashSet<>();

        while (p != null) {
            path.add(p);
            p = parents.get(p);
        }

        while (q != null) {
            if (path.contains(q)) {
                break;
            }
            q = parents.get(q);
        }

        return q;
    }
}
