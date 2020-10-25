package com.example.leetcode;

/**
 * 数组中的最长山脉（DP）
 * https://leetcode-cn.com/problems/longest-mountain-in-array/
 */
public class LeetCode845 {
    class Solution {
        public int longestMountain(int[] A) {
            int result = 0;
            if (null != A && A.length >= 3) {
                int n = A.length;

                // 最左山峰，递增
                int[] left = new int[n];
                for (int i = 1; i < n - 1; i++) {
                    left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
                }

                // 最右山峰，递减
                int[] right = new int[n];
                for (int i = n - 2; i >= 0; i--) {
                    right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
                }

                // 统计山峰长度
                for (int i = 0; i < n; i++) {
                    if (left[i] > 0 && right[i] > 0) {
                        result = Math.max(result, left[i] + right[i] + 1);
                    }
                }
            }
            return result;
        }
    }
}
