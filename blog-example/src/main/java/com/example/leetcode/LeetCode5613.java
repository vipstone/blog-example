package com.example.leetcode;

public class LeetCode5613 {
    class Solution {
        public int maximumWealth(int[][] accounts) {
            if (accounts == null) return 0;
            int ans = 0;
            for (int i = 0; i < accounts.length; i++) {
                // 当前项的累计值
                int sum = 0;
                for (int j = 0; j < accounts[i].length; j++) {
                    sum += accounts[i][j];
                }
                // 当前累计值和最大值进行比较并设置最大值
                ans = Math.max(ans, sum);
            }
            return ans;
        }
    }
}
