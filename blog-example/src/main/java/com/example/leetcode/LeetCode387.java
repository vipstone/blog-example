package com.example.leetcode;

public class LeetCode387 {
    class Solution {
        public int firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            int ans = 0;
            for (char c : chars) {
                if (s.indexOf(c) != s.lastIndexOf(c)) {
                    return ans;
                }
                ans++;
            }
            return -1;
        }
    }
}
