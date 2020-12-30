package com.example.leetcode;

import java.util.Arrays;

/**
 * 1046. 最后一块石头的重量
 * https://leetcode-cn.com/problems/last-stone-weight/
 */
public class LeetCode1046 {
    class Solution {
        public int lastStoneWeight(int[] stones) {
            // 升序排序
            Arrays.sort(stones);
            // 小标最大值
            int len = stones.length - 1;
            // 循环整个数组
            for (int i = len; i > 0; i--) {
                // 将最后一个值设置为上一轮的最大值-次大值
                stones[len] = stones[len] - stones[len - 1];
                // 将次大值设置为 0
                stones[len - 1] = 0;
                // 重新排序（升序）
                Arrays.sort(stones);
            }
            // 返回最后的结果
            return stones[len];
        }
    }
}
