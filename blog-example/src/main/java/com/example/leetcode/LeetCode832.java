package com.example.leetcode;

/**
 * 832. 翻转图像
 * https://leetcode-cn.com/problems/flipping-an-image/
 */
public class LeetCode832 {
    class Solution {
        public int[][] flipAndInvertImage(int[][] A) {
            // 创建新数组
            int[][] ans = new int[A.length][A[0].length];
            for (int i = 0; i < A.length; i++) {
                int c = 0;
                // 翻转数组
                for (int j = A[i].length - 1; j >= 0; j--) {
                    // 反转图片
                    ans[i][c++] = (A[i][j] == 0 ? 1 : 0);
                }
            }
            return ans;
        }
    }
}
