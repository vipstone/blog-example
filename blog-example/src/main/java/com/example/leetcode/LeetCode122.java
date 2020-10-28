package com.example.leetcode;

/**
 * 买卖股票的最佳时机 II
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class LeetCode122 {

    // 方法一
    class Solution {
        public int maxProfit(int[] prices) {
            int mp = 0; // 最终盈利
            int min = -1; // 相对最小值
            int max = 0; // 相对最大值
            for (int i = 1; i < prices.length; i++) {
                if (prices[i - 1] < prices[i]) {
                    min = prices[i - 1];
                    max = prices[i];
                }
                // 查询是否到了卖出的最高点
                if (i < prices.length - 1 && min > -1) {
                    int j = i + 1;
                    for (; j < prices.length; j++) {
                        if (prices[j - 1] > prices[j]) {
                            // 已经找到了最大卖点，后一个值小于前一个值
                            max = prices[j - 1];
                            break;
                        } else {
                            // 一直在涨，后一个值大于前一个值
                            max = prices[j];
                        }
                    }
                    if ((prices.length - 1 - j) < 1) {
                        // 已经循环完，或后面剩一个小值
                        mp += max - min;
                        return mp;
                    } else {
                        // 累计一次买入和卖出盈利
                        mp += max - min;
                        // 初始化值
                        min = -1;
                        max = 0;
                        i = j;
                    }
                }
                // 计算未累加的收益
                if (min != -1) mp += max - min;
            }
            return mp;
        }
    }

    // 贪心算法
    class Solution2 {

        public int maxProfit(int[] prices) {
            int mp = 0;
            for (int i = 1; i < prices.length; i++) {
                // 和前一个数值计算价差
                int m = prices[i] - prices[i - 1];
                // 有钱赚就入账
                if (m > 0) mp += m;
            }
            return mp;
        }
    }

}
