package com.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 有多少小于当前数字的数字
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class LeetCode1365 {

    class Solution {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            if (nums == null || nums.length <= 0) return null;
            // 1.使用新数组排序
            int[] nsort = Arrays.copyOf(nums, nums.length);
            Arrays.sort(nsort);

            // 2.存储小于此数的个数字典
            HashMap<Integer, Integer> map = new HashMap();
            // 一定要倒序，这样可以将重复的元素值覆盖
            for (int i = nsort.length - 1; i >= 0; i--) {
                map.put(nsort[i], i);
            }
            
            // 3.构建最终结果数组
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[i] = map.get(nums[i]);
            }
            return result;
        }
    }
}
