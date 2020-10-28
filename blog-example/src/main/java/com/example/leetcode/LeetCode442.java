package com.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数组中重复的数据
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class LeetCode442 {
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> list = new ArrayList<>();
            HashMap<Integer, Boolean> map = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) list.add(nums[i]);
                map.put(nums[i], true);
            }
            return list;
        }
    }
}
