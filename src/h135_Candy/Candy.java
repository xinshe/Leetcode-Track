package h135_Candy;

import java.util.Arrays;

/**
 * 135. 分发糖果
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *      每个孩子至少分配到 1 个糖果。
 *      相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *      输入: [1,0,2]
 *      输出: 5
 *      解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2:
 *      输入: [1,2,2]
 *      输出: 4
 *      解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * 题目链接：https://leetcode-cn.com/problems/candy/
 * 参考：
 * https://leetcode-cn.com/problems/candy/solution/fen-fa-tang-guo-by-leetcode/
 */
public class Candy {

    /**
     * 解法1：暴力法
     *
     * 算法:
     * 最简单的方法是使用一个一维的数组 candies 去记录给学生的糖果数。首先我们给每个学生 1 个糖果。然后我们开始从
     * 左到右扫描数组。对每一个学生，如果当前的评分 ratings[i] 比前一名学生的评分 ratings[i - 1] 高，且
     * candies[i]<=candies[i - 1]，那么我们更新 candies[i] = candies[i-1] + 1。这样，这两名学生之间的糖果分配目前是
     * 正确的。同样的，如果当前的评分 ratings[i] 比后一名学生的评分 ratings[i+1] 高，且 candies[i]<=candies[i + 1]，
     * 我们同样更新 candies[i]=candies[i+1] + 1。
     * 我们继续对 ratings 数组重复此步骤。如果在某次遍历中，candies 数组不再变化，意味着我们已经得到了最后的糖果分布，
     * 此时可以停止遍历。为了记录是否到达最终状态，我们用 flag 记录每次遍历是否有糖果数目变化，如果有，则为 True ，
     * 否则为 False 。
     * 最终，我们可以把 candies 数组中所有糖果数目加起来，得到要求数目最少的糖果数。
     *
     * 复杂度分析:
     *      时间复杂度：O(n^2) 。对于每个元素，我们最多要遍历 n 次。
     *      空间复杂度：O(n)。需要一个长度为 n 的 candies 数组。
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < ratings.length; i++) {
                if (i >= 1 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    flag = true;
                }
                if (i < ratings.length - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    flag = true;
                }
            }
        }
        int res = 0;
        for (int candy : candies) {
            res += candy;
        }
        return res;
    }

    /**
     * 方法2：用两个数组
     *
     * 算法：
     * 在这种方法中，我们使用两个一维数组 left2right 和 right2left。
     * 数组 left2right 用来存储每名学生只与左边邻居有关的所需糖果数。也就是假设规则为：如果一名学生评分比他左边学生高，
     * 那么他应该比他左边学生得到更多糖果。
     * 数组 right2left 数组用来保存只与右边邻居有关的所需糖果数。也就是假设规则为：如果一名学生评分比他右边学生高，
     * 那么他应该比他右边学生得到更多糖果。
     * 首先，我们在 left2right 和 right2left 中，给每个学生 1 个糖果。然后，我们从左向右遍历整个数组，只要当前学生评分
     * 比他左邻居高，我们在 left2right 数组中更新当前学生的糖果数 left2right[i] = left2right[i-1] + 1，这是因为在每次
     * 更新前，当前学生的糖果数一定小于等于他左邻居的糖果数。
     * 在从左到右扫描后，我们用同样的方法从右到左只要当前学生的评分比他右边（第 (i+1) 个）学生高，就更新 right2left[i] 为
     * right2left[i] = right2left[i + 1] + 1。
     *
     * 现在，对于数组中第 i 个学生，为了满足题中条件，我们需要给他 max(left2right[i], right2left[i]) 个糖果。因此，
     * 最后累加每一项得到的和即为最少糖果数。
     *
     * 复杂度分析：
     *      时间复杂度：O(n)。 left2right 和 right2left 会各更新一次。
     *      空间复杂度：O(n)。 两个数组 left2right 和 right2left 大小都为 n 。
     */
    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        int res = 0;
        for (int i = 0; i < ratings.length; i++) {
            res += Math.max(left2right[i], right2left[i]);
        }
        return res;
    }

    /**
     * 解法3：用一个数组（推荐）
     *
     * 复杂度分析：
     *      时间复杂度：O(n)。长度为 n 数组 candies 被遍历了 2 次。
     *      空间复杂度：O(n)。数组 candies 长度为n。
     *
     */
    public int candy3(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        // 先从左到右遍历
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // 再从右到左，并做累加
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }

    /**
     * 方法 4：常数空间一次遍历
     *
     * 不会！
     */
}
