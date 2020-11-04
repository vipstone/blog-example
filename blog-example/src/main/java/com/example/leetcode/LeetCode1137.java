package com.example.leetcode;

/**
 * 1137. 第 N 个泰波那契数
 * https://leetcode-cn.com/problems/n-th-tribonacci-number/
 */
public class LeetCode1137 {
    class Solution {
        public int tribonacci(int n) {
            if (n == 0) return 0;
            if (n == 1 || n == 2) return 1;
            int t0 = 0, t1 = 1, t2 = 1;
            int res = 0;
            for (int i = 3; i <= n; i++) {
                res = t0 + t1 + t2;
                t0 = t1;
                t1 = t2;
                t2 = res;
            }
            return res;
        }
    }
}
