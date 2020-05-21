package e653_TwoSumIV_InputIsABST;

import helper.TreeNode;

import java.util.ArrayList;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 * 输出: True
 *
 * 案例 2:
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 * 输出: False
 *
 */

public class TwoSumIV {

    /**
     * 使用中序遍历得到有序数组之后，再利用双指针对数组进行查找。
     * 应该注意到，这一题不能用分别在左右子树两部分来处理这种思想，因为两个待求的节点可能分别在左右子树中。
     */
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        treeToArray(arrayList, root);
        int p1 = 0, p2 = arrayList.size() - 1;
        int sum = 0;
        while (p1 < p2) {
            sum = arrayList.get(p1) + arrayList.get(p2);
            if (sum == k) return true;
            else if (sum < k) p1++;
            else p2--;
        }
        return false;
    }

    // 中序遍历
    private void treeToArray(ArrayList<Integer> arrayList, TreeNode root) {
        if (root != null) {
            treeToArray(arrayList, root.left);
            arrayList.add(root.val);
            treeToArray(arrayList, root.right);
        }
    }
}
