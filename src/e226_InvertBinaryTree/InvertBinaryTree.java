package e226_InvertBinaryTree;

import helper.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 题目链接：https://leetcode-cn.com/problems/invert-binary-tree/
 */

public class InvertBinaryTree {

    /**
     * 方法1：递归（DFS思想）
     * 既然树中的每个节点都只被访问一次，那么时间复杂度就是 O(n)，其中 n 是树中节点的个数。在反转之前，不论怎样我们
     * 至少都得访问每个节点至少一次，因此这个问题无法做地比 O(n) 更好了。
     *
     * 本方法使用了递归，在最坏情况下栈内需要存放 O(h) 个方法调用，其中 h 是树的高度。由于 h∈O(n)，可得出空间复杂度为
     * O(n)。
     *
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left; // // 后面的操作会改变 left 指针，因此先保存下来
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    /**
     * 方法2：迭代（BFS思想）
     *
     * 这个方法的思路就是，我们需要交换树中所有节点的左孩子和右孩子。因此可以创一个队列来存储所有左孩子和右孩子还没有被
     * 交换过的节点。开始的时候，只有根节点在这个队列里面。只要这个队列不空，就一直从队列中出队节点，然后互换这个节点的
     * 左右孩子节点，接着再把孩子节点入队到队列，对于其中的空节点不需要加入队列。最终队列一定会空，这时候所有节点的孩子
     * 节点都被互换过了，直接返回最初的根节点就可以了。
     *
     */
    public TreeNode invertTree02(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right !=  null) queue.add(cur.right);
        }
        return root;
    }

    /**
     * 方法3：
     *
     * 扩展：利用前中后遍历来做
     * https://leetcode-cn.com/problems/invert-binary-tree/solution/qian-zhong-hou-bian-li-by-vailing/
     */
    public TreeNode invertTree03(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
