package com.example.leetcode;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 */
public class Offer53_I {
    // 方法一:暴力解法
    class Solution {
        public int search(int[] nums, int target) {
            int ans = 0;
            for (int i : nums) {
                if (i == target) {
                    ans++;
                }
            }
            return ans;
        }
    }
}
