package com.example.leetcode;

import java.util.Arrays;

/**
 * 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class LeetCode4 {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int leng = nums1.length + nums2.length;
            int[] res = new int[leng];
            System.arraycopy(nums1, 0, res, 0, nums1.length);
            System.arraycopy(nums2, 0, res, nums1.length, nums2.length);
            Arrays.sort(res);
            if (leng % 2 == 0) {
                // 偶数
                return (res[leng / 2 - 1] + res[leng / 2]) / 2.0;
            } else {
                // 奇数
                return res[leng / 2];
            }
        }
    }
}
