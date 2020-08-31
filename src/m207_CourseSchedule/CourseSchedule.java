package m207_CourseSchedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表（判断有向图是否有环）
 *
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 题目链接：https://leetcode-cn.com/problems/course-schedule
 */
public class CourseSchedule {

    /**
     * 方法一：入度表（广度优先遍历）
     *
     * 算法流程：
     *      1. 统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     *      2. 借助一个队列 queue，将所有入度为 0 的节点入队。
     *      3. 当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
     *              并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 next 的入度 -1，即 indegrees[next] -= 1。
     *              当入度-1 后邻接节点 next 的入度为0，说明 next 所有的前驱节点已经被 “删除”，此时将 next 入队。
     *      4. 在每次 pre 出队时，执行 numCourses--；
     *              若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为0。
     *              因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
     * 复杂度分析：
     * 时间复杂度 O(N + M)： 遍历一个图需要访问所有节点和所有临边，N 和 M 分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)： 为建立邻接表所需额外空间，adjacency 长度为 N ，并存储 M 条临边的数据。
     *
     * 参考：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null
                || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        for (int[] relation : prerequisites) {
            indegrees[relation[0]]++;
            adjacency.get(relation[1]).add(relation[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;

            for (int next : adjacency.get(pre)) {
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return numCourses == 0;
    }

    /**
     * 方法二：深度优先遍历
     * 原理是通过 DFS 判断图中是否有环。
     *
     * 算法流程：
     *      1. 借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     *              1. 未被 DFS 访问：i == 0；（未搜索）
     *              2. 已被其他节点启动的 DFS 访问：i == -1；（已完成）
     *              3. 已被当前节点启动的 DFS 访问：i == 1。（搜索中）
     *      2. 对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 True。DFS 流程如下：
     *              1. 终止条件：
     *                      当 flags[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 False。
     *                      当 flags[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环 ，直接返回 True。
     *              2. 将当前访问节点 i 对应 flags[i] 置 1，即标记其被本轮 DFS 访问过；
     *              3. 递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 True；
     *              4. 当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 −1 并返回 False。
     *      3. 若整个图 DFS 结束并未发现环，返回 False。
     *
     * 复杂度分析：
     * 时间复杂度 O(N + M)： 遍历一个图需要访问所有节点和所有临边，N 和 M 分别为节点数量和临边数量；
     * 空间复杂度 O(N + M)： 为建立邻接表所需额外空间，adjacency 长度为 N ，并存储 M 条临边的数据。
     *
     * 参考：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null
                || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }
        int[] flags = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            adjacency.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(adjacency, flags, i)) return false;
        }
        return true;
    }

    // 返回 true 表示有环
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int start) {
        if (flags[start] == -1) return false;
        if (flags[start] == 1) return true; // 表示有环
        flags[start] = 1;
        for (int adj : adjacency.get(start)) {
            if (dfs(adjacency, flags, adj)) return true;
        }
        flags[start] = -1;
        return false;
    }
}
