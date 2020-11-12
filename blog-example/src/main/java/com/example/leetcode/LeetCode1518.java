package com.example.leetcode;

/**
 * 1518. 换酒问题
 * https://leetcode-cn.com/problems/water-bottles/
 */
public class LeetCode1518 {

    // 递归
    class Solution {
        int total = 0;

        public int numWaterBottles(int numBottles, int numExchange) {
            // 有酒就喝
            total += numBottles;
            // 本次可兑换的酒
            int n = numBottles / numExchange;
            if (n == 0) {
                return total;
            } else {
                // 本轮剩余酒
                int remain = numBottles % numExchange;
                // 本轮剩余酒等下轮喝
                total -= remain;
                return numWaterBottles(n + (remain), numExchange);
            }
        }
    }

    // 贪心(/ 和 %)
    class Solution2 {
        public int numWaterBottles(int numBottles, int numExchange) {
            // 总计酒瓶数
            int total = numBottles;
            // 有酒瓶就兑换
            while (numBottles >= numExchange) {
                // 可兑换的新酒
                int n = numBottles / numExchange;
                // 有酒就喝
                total += n;
                // 剩余酒瓶(剩余未兑换 + 已兑换喝掉的)
                numBottles = numBottles % numExchange + n;
            }
            return total;
        }
    }

    // 贪心(+ 和 -)
    class Solution3 {
        public int numWaterBottles(int numBottles, int numExchange) {
            // 总计酒瓶数
            int total = numBottles;
            // 有酒瓶就兑换
            while (numBottles >= numExchange) {
                // 执行一轮兑换
                numBottles -= numExchange;
                ++total;
                // 兑换一次多一个酒瓶
                ++numBottles;
            }
            return total;
        }
    }
}
