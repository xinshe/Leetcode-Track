# 解法1：动态规划

dp[0] = 0 

dp[1] = dp[1 - 1\*1] + 1 = 1

dp[2] = dp[2 - 1\*1] + 1 = dp[1] + 1 = 2

dp[3] = dp[3 - 1 \* 1] + 1 = dp[2] + 1 = 3

dp[4] = Min{ dp[4 - 1\*1] + 1,  dp[4 - 2\*2] +1 } = Min{ dp[3] + 1, dp[0] + 1 } = 1

dp[5] = Min{ dp[5 - 1\*1] + 1,  dp[5 - 2\*2] + 1 }  = Min{ dp[4] + 1, dp[1] + 1 }  = 2

......

dp[13] = Min{ dp[13 - 1\*1] + 1, dp[13 - 2\*2] + 1,  dp[13 - 3\*3] + 1 }  = Min{ dp[12] + 1, dp[9] + 1, dp[4] + 1 }  = 2

......

## 状态转移方程

dp[n] = Min{ dp[n - i\*i] + 1 } ,  n - i*i >=0 && i >= 1

# 参考

[东大ACM退役队伍解法](https://mp.weixin.qq.com/s/AB7NfUVHgJUHTiTZEu4xzw)

[动态规划解法1](https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java)

[动态规划解法2](https://leetcode.com/problems/perfect-squares/discuss/71632/Beautiful-8-Lines-Java-Solution)

[BFS解法1](https://leetcode.com/problems/perfect-squares/discuss/71520/Java-Concise-BFS)

[BFS解法2](https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3.md#bfs)

