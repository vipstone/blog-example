package com.example.leetcode;

import java.util.Arrays;

/**
 * 217. 存在重复元素
 * https://leetcode-cn.com/problems/contains-duplicate/
 */
public class LeetCode217 {
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            return Arrays.stream(nums).distinct().toArray().length != nums.length;
        }
    }
}
