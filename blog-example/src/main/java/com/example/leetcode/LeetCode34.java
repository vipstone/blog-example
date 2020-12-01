package com.example.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class LeetCode34 {
    // 方法一:暴力解法
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int s = -1, l = -1;
            for (int i = 0; i < nums.length; i++) {
                // 从前往后判断
                if (s == -1 && nums[i] == target) {
                    s = i;
                }
                // 从后往前判断
                if (l == -1 && nums[nums.length - 1 - i] == target) {
                    l = nums.length - 1 - i;
                }
                // 如果都已经找到,退出循环
                if (s != -1 && l != -1) break;
            }
            int[] ans = {s, l};
            return ans;
        }
    }

}
