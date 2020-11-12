package com.example.leetcode;

/**
 * 905. 按奇偶排序数组
 * https://leetcode-cn.com/problems/sort-array-by-parity/
 */
public class LeetCode905 {
    class Solution {
        public int[] sortArrayByParity(int[] A) {
            // 快指针(存偶数)
            int front = 0;
            // 快指针(存奇数)
            int back = A.length - 1;
            int[] ans = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                if (A[i] % 2 == 0) {
                    // 偶数
                    ans[front] = A[i];
                    ++front;
                } else {
                    ans[back] = A[i];
                    --back;
                }
            }
            return ans;
        }
    }
}
