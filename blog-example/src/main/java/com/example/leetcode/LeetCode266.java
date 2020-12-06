package com.example.leetcode;

import java.util.HashMap;

/**
 * 266. 回文排列
 * https://leetcode-cn.com/problems/palindrome-permutation/
 */
public class LeetCode266 {
    class Solution {
        public boolean canPermutePalindrome(String s) {
            char[] arr = s.toCharArray();
            HashMap<Character, Character> map = new HashMap();
            // 将字符存入或移除 HashMap,如果已经存在了一个则移除,如果不存在则加入
            for (char i : arr) {
                if (map.containsKey(i)) {
                    map.remove(i);
                } else {
                    map.put(i, i);
                }
            }
            // 最终剩余一个以下则证明为回文,否则为非回文
            return map.size() <= 1;
        }
    }
}
