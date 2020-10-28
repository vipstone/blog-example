package com.example.leetcode;

/**
 * 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class LeetCode121 {
    // 暴力法
    class Solution {
        public int maxProfit(int[] prices) {
            // 最高盈利
            int mp = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int item = prices[j] - prices[i];
                    if (item > mp) mp = item;
                }
            }
            return mp;
        }
    }

    // 找到最低价或最大盈利差
    class Solution2 {
        public int maxProfit(int prices[]) {
            if (prices == null || prices.length == 0) return 0;
            int mp = 0; // 最高盈利
            int min = prices[0]; // 最低价格
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < min) {
                    min = prices[i];
                } else if (mp < (prices[i] - min)) {
                    // 高于最高盈利
                    mp = prices[i] - min;
                }
            }
            return mp;
        }
    }
}
