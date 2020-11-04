package com.example.leetcode;

/**
 * 53. 最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class LeetCode53 {

    // 方法一:贪心解法,每轮取相对较大值
    class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length <= 0) return 0;
            int max = nums[0];
            int prev = 0;
            for (int n : nums) {
                prev = Math.max(prev + n, n);
                max = Math.max(prev, max);
            }
            return max;
        }
    }

    // DP,状态转移方程式: f(i)= Max(f(i-1),f(i))
    class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length <= 0) return 0;
            int max = nums[0], prev = 0;
            for (int i = 0; i < nums.length; i++) {
                // max(f(i-1))
                prev = Math.max(prev + nums[i], nums[i]);
                // f(i)= Max(f(i-1),f(i))
                max = Math.max(prev, max);
            }
            return max;
        }
    }
}
