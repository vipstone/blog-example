package com.example.leetcode;

/**
 * 557. 反转字符串中的单词 III
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 */
public class LeetCode557 {
    class Solution {
        public String reverseWords(String s) {
            // 根据空格转为数组
            String[] arr = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String i : arr) {
                // 当前字符串长度
                int l = i.length();
                char[] as = i.toCharArray();
                // 双指针替换
                for (int j = 0; j < l / 2; j++) {
                    char t = as[j];
                    as[j] = as[l - j - 1];
                    as[l - j - 1] = t;
                }
                sb.append(as);
                sb.append(" ");
            }
            return sb.toString().trim();
        }
    }
}
