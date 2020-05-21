package e501_FindModeInBinarySearchTree;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *      结点左子树中所含结点的值小于等于当前结点的值
 *      结点右子树中所含结点的值大于等于当前结点的值
 *      左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 */

public class FindModeInBinarySearchTree {

    private TreeNode preNode = null;
    private int curCount = 1;
    private int maxCount = 1;

    public int[] findMode(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int[] res = new int[nums.size()];
        int idx = 0;
        for (int num : nums) {
            res[idx++] = num;
        }
        return res;
    }

    private void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inOrder(root.left, nums);
        if (preNode != null) {
            if (preNode.val == root.val) curCount++;
            else curCount = 1;
        }
        if (curCount > maxCount) {
            maxCount = curCount;
            nums.clear();
            nums.add(root.val);
        } else if (curCount == maxCount) {
            nums.add(root.val);
        }
        preNode = root;
        inOrder(root.right, nums);
    }


}
