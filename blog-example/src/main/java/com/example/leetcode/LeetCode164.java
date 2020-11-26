package com.example.leetcode;

import java.util.Arrays;

/**
 * 164. 最大间距
 * https://leetcode-cn.com/problems/maximum-gap/
 */
public class LeetCode164 {
    class Solution {
        public int maximumGap(int[] nums) {
            Arrays.sort(nums);
            int ans = 0;
            for (int i = 1; i < nums.length; i++) {
                ans = Math.max(ans, nums[i] - nums[i - 1]);
            }
            return ans;
        }
    }
}
