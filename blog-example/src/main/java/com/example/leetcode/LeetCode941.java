package com.example.leetcode;

/**
 * 941. 有效的山脉数组
 * https://leetcode-cn.com/problems/valid-mountain-array/
 */
public class LeetCode941 {
    class Solution {
        public boolean validMountainArray(int[] A) {
            if (A != null && A.length >= 3) {
                if (A[1] - A[0] <= 0) return false;
                boolean flag = true; // 升序标识
                for (int i = 2; i < A.length; i++) {
                    // 不能有相同的值
                    if (A[i] - A[i - 1] == 0) return false;
                    if (flag && A[i] - A[i - 1] > 0) {
                        // 上升山峰
                        continue;
                    } else if (flag && A[i] - A[i - 1] < 0) {
                        // 初次转为降序
                        flag = false;
                    } else if (!flag && A[i] - A[i - 1] < 0) {
                        // 下降山峰
                        continue;
                    } else if (!flag && A[i] - A[i - 1] >= 0) {
                        // 下降途中遇到非正常元素
                        return false;
                    }
                }
                // 循环完成,并经历了上升和下降之后的正常山峰
                if (!flag) return true;
            }
            return false;
        }
    }
}
