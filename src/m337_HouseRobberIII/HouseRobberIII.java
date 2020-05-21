package m337_HouseRobberIII;

import helper.TreeNode;

/**
 * 337. 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似
 * 于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 题目链接：https://leetcode-cn.com/problems/house-robber-iii/
 */

public class HouseRobberIII {

    /**
     * 方法1：递归
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val1 = root.val;
        if (root.left != null) val1 += (rob(root.left.left) + rob(root.left.right));
        if (root.right != null) val1 += (rob(root.right.left) + rob(root.right.right));
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

    /**
     * 方法2：树状DP（没有理解）
     * 参考：https://leetcode-cn.com/problems/house-robber-iii/solution/jian-dan-gao-xiao-de-shu-zhuang-dpzi-di-xiang-shan/
     */
    public int rob2(TreeNode root) {
        return doRob(root).val;
    }

    private TreeNode doRob(TreeNode root) {
        if (root == null) {
            TreeNode newNode = new TreeNode(0);
            return doRob(newNode);
        }
        if (root.left == null && root.right == null) {
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            return root;
        }
        root.left = doRob(root.left);
        root.right = doRob(root.right);
        root.val = Math.max(root.left.val + root.right.val,
                root.val + root.left.left.val + root.left.right.val + root.right.left.val + root.right.right.val);

        return root;
    }
}
