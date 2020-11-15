package com.example.leetcode;

/**
 * 344. 反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class LeetCode344 {
    class Solution {
        // 双指针解法
        public void reverseString(char[] s) {
            int leng = s.length;
            for (int i = 0; i < leng / 2; i++) {
                // 临时变量（记录左边）
                char t = s[i];
                s[i] = s[leng - 1 - i];
                s[leng - 1 - i] = t;
            }
        }
    }
}
