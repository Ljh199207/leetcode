package com.ljh.simple;

/**
 * @author ljh  爬楼梯
 * 动态规划
 * @date 2020-04-26 11:41
 * 输入：2
 * 输出：2
 * 解释： 有两种方法可以爬到楼顶。
 * <p>
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 问题拆解：
 * <p>
 * 我们到达第 n 个楼梯可以从第 n – 1 个楼梯和第 n – 2 个楼梯到达，
 * 因此第 n 个问题可以拆解成第 n – 1 个问题和第 n – 2 个问题，
 * 第 n – 1 个问题和第 n – 2 个问题又可以继续往下拆，
 * 直到第 0 个问题，也就是第 0 个楼梯 (起点)
 */
public class solution70 {


    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
