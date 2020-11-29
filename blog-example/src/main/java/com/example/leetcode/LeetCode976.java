package com.example.leetcode;

import java.util.Arrays;

/**
 * 976. 三角形的最大周长
 * https://leetcode-cn.com/problems/largest-perimeter-triangle/
 */
public class LeetCode976 {
    class Solution {
        public int largestPerimeter(int[] A) {
            Arrays.sort(A);
            for (int i = A.length - 1; i >= 2; i--) {
                // 三角形任意两边之和大于第三边
                if (A[i] < A[i - 1] + A[i - 2]) {
                    return A[i] + A[i - 1] + A[i - 2];
                }
            }
            return 0;
        }
    }
}
