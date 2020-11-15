package com.example.leetcode;

/**
 * 9. 回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class LeetCode9 {
    // 暴力解法,得到两部分字符进行比较
    class Solution {
        public boolean isPalindrome(int x) {
            // 先转换为字符
            String s = x + "";
            // 字符串长度
            int leng = s.length();
            // 分隔成两部分
            char[] f = s.substring(0, leng / 2).toCharArray();
            char[] b = s.substring(leng / 2, leng).toCharArray();
            // 依次对比
            for (int i = 0; i < f.length; i++) {
                if (f[i] != b[b.length - 1 - i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
