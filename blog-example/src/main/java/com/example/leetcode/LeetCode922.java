package com.example.leetcode;

/**
 * 922. 按奇偶排序数组 II
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 */
public class LeetCode922 {
    class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int[] ans = new int[A.length];
            // 奇数下标(指针)
            int s = 1;
            // 偶数下标(指针)
            int d = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] % 2 == 0) {
                    // 偶数
                    ans[d] = A[i];
                    d += 2;
                } else {
                    ans[s] = A[i];
                    s += 2;
                }
            }
            return ans;
        }
    }
}
