package com.example.leetcode;

/**
 * todo:714. 买卖股票的最佳时机含手续费[不通过,有问题]
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class LeetCode714 {
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            int res = 0; // 最大收益
            int min = -1;
            for (int p : prices) {
                if (p < min) {
                    // 值相对较小
                    min = p;
                } else if ((p - min) > fee) {
                    // 有收益
                    res = (p - min) - fee;
                }
            }
            return res;
        }
    }
}
