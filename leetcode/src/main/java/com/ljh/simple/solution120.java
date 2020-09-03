package com.ljh.simple;

import java.util.List;

/**
 * @author ljh
 * @date 2020-04-26 13:43
 * 三角形最小路径和。
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 动态规划 从下往上 d[i][j] = Math.min(d[i+1][j],d[i+1][j+1])+triangle[i][j]
 */
public class solution120 {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> lastRow = triangle.get(n);
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = lastRow.get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + row.get(j);

            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {

    }
}
