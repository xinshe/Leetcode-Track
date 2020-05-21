package e437_PathSumIII;

import helper.TreeNode;
import java.util.ArrayList;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 */

public class PathSumIII {

    /**
     * 方法1：双重递归
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumStartWithRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumStartWithRoot(TreeNode root, int sum) {
        if (root == null) return 0;
        int ret = 0;
        sum -= root.val;
        if (sum == 0) ret++;
        ret += pathSumStartWithRoot(root.left, sum) + pathSumStartWithRoot(root.right, sum);
        return ret;
    }

    /**
     * 方法2：一个递归
     *
     * 思想是遍历到某个节点时，检查该节点到根节点的路径上包含该节点的所有可能，如此每一个节点在检查可能的路径时都不会重复。
     * 比如假设有路径1,2,3,4，-4,5，sum=5，如果遍历到3时，只需要检查1,2,3中是否有包含3的解，发现2,3是一个解；
     * 遍历到5时只需检查包含5的解，包括5，以及4，-4,5两个解。这样就没有重复检查。因为包含5的解在之前一定没被检查过。
     *
     * 检查方式也是极其简单，使用 ArrayList 保存遍历过程中的值，然后反向for循环这个 ArrayList 求和即可。
     *
     * 这种方法要比第一种方法快很多
     */
    private int ret = 0;

    public int pathSum2(TreeNode root, int sum) {
        ArrayList<Integer> data = new ArrayList<>();
        paths(root, sum, data);
        return ret;
    }

    private void paths(TreeNode root, int sum, ArrayList<Integer> data) {
        if (null == root) return;
        data.add(root.val);
        int cur = 0;
        for (int i = data.size() - 1; i >= 0; i--) { // //检查包含root.val的解的个数
            cur += data.get(i);
            if (cur == sum) ret++;
        }
        paths(root.left, sum, data);
        paths(root.right, sum, data);
        data.remove(data.size()-1);
    }
}
