package e257_BinaryTreePaths;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> track = new ArrayList<>();
        backtrack(track, res, root);
        return res;
    }

    private void backtrack(List<Integer> track, List<String> res, TreeNode node) {
       if (node == null) return;

       track.add(node.val);
       if (node.left == null && node.right == null) {
           res.add(buildString(track));
       } else {
           backtrack(track, res, node.left);
           backtrack(track, res, node.right);
       }
       track.remove(track.size() - 1);
    }

    private String buildString(List<Integer> track) {
        StringBuilder s = new StringBuilder();
        for (int item : track) {
            s.append(item).append("->");
        }
        s.delete(s.length() - 2, s.length());
        return s.toString();
    }
}
