package m105_ConstructBinaryTreeFromPreorderAndInorderTraversal;

import helper.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int start1, int end1, int[] inorder, int start2, int end2) {
        if (end1 < start1 || end2 < start2) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start1]);
        int p = start2 ;
        for (; p <= end2; p++) {
            if (inorder[p] == preorder[start1]) {
                break;
            }
        }
        root.left = build(preorder, start1 + 1, start1 + p - start2,
                inorder, start2, p - 1);
        root.right = build(preorder, start1 + p - start2 + 1, end1,
                inorder, p + 1, end2);
        return root;
    }
}
