package com.example.leetcode;

/**
 * 1662. 检查两个字符串数组是否相等
 * https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/
 */
public class LeetCode1622 {
    // 方法一:遍历将字符串整合之后对比
    class Solution {
        public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            for (String i : word1) {
                s1.append(i);
            }
            for (String i : word2) {
                s2.append(i);
            }
            return s1.toString().equals(s2.toString());
        }
    }

    // 方法二:使用内置方法合并字符串
    class Solution2 {
        public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            return String.join("", word1).toString().equals(String.join("", word2));
        }
    }
}
