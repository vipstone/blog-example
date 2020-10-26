package com.example.leetcode;

/**
 * 滑动窗口的最大值
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
public class Offer59 {

    // 方法一，双重遍历，性能差
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k <= 0) return new int[0];
            // 结果数组
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < result.length; i++) {
                int max = nums[i]; // 初始化最大值
                // 循环 k-1 次找最大值
                for (int j = i + 1; j < (i + k); j++) {
                    max = Math.max(max, nums[j]);
                }
                result[i] = max;
            }
            return result;
        }
    }

    // 方法二，双重遍历，改良版
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k <= 0) return new int[0];
            // 结果数组
            int[] result = new int[nums.length - k + 1];
            int rn = -Integer.MAX_VALUE; // 记录上次移除的值
            int max = rn; // 滑动窗口最大值（初始化）
            for (int i = 0; i < result.length; i++) {
                if (rn == max) { // 上一次将最大值移除，重新判断最大值
                    max = nums[i]; // 初始化最大值
                    // 循环 k-1 次找最大值
                    for (int j = i + 1; j < (i + k); j++) {
                        max = Math.max(max, nums[j]);
                    }
                } else { // 上次没有将最大值移除
                    // 比较上一个滑动块最大值和新增值
                    max = Math.max(max, nums[i + k - 1]);
                }
                result[i] = max;
                // 记录本轮带移除数值
                rn = nums[i];
            }
            return result;
        }
    }

}
