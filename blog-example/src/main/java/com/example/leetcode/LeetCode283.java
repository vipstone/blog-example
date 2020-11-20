package com.example.leetcode;

/**
 * 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class LeetCode283 {
    class Solution {
        public void moveZeroes(int[] nums) {
            // 非零下标
            int idx = 0;
            // 将非零元素移动到数组头部
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[idx++] = nums[i];
                }
            }
            // 将 0 移动到数组尾部
            for (int i = idx; i < nums.length; i++) {
                nums[idx++] = 0;
            }
        }
    }
}
