package com.example.leetcode;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 */
public class LeetCode242 {
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;
            char[] s_arr = s.toCharArray();
            char[] t_arr = t.toCharArray();
            // 两个字符排序
            Arrays.sort(s_arr);
            Arrays.sort(t_arr);
            return Arrays.equals(s_arr, t_arr);
        }
    }
}
