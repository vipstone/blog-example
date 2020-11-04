package com.example.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 349. 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class LeetCode349 {
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            // int[] 转为 List
            List<Integer> l1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
            // 过滤，只保留并集元素
            List<Integer> l2 = Arrays.stream(nums2).filter(n -> l1.contains(n)).distinct()
                    .boxed().collect(Collectors.toList());
            // List 转为 int[]
            return l2.stream().mapToInt(Integer::valueOf).toArray();
        }
    }
}
