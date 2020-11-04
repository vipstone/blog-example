package com.example.leetcode;

/**
 * 70.爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class LeetCode70 {
    class Solution {
        // 使用 DP | 状态转移方程式:f(i)=f(i-1)+f(i-2)
        public int climbStairs(int n) {
            // 初始值
            if (n <= 2) return n;
            // f2 = f(i-2)
            int f2 = 1;
            // f1 = f(i-1)
            int f1 = 2;
            int res = 0;
            for (int i = 2; i < n; i++) {
                // 状态转移方程式:f(i)=f(i-1)+f(i-2)
                res = f1 + f2;
                // 保存下一轮 f(i-1) 和 f(i-2) 的值,重复利用
                f2 = f1;
                f1 = res;
            }
            return res;
        }
    }
}
