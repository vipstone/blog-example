package com.example.leetcode;


import java.util.Arrays;

public class LeetCode1370 {
    // 方法一:递归
    class Solution {
        public String sortString(String s) {
            // 递归获取最终答案
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return recursion("", arr);
        }

        private String recursion(String ans, char[] arr) {
            boolean flag = true;

            // 从小到大遍历
            char min = '1'; // 存储当前已经使用的最小值
            for (int i = 0; i < arr.length; i++) {
                // 不动于空值(0),也不等于重复的值
                if (arr[i] != '0' && arr[i] != min) {
                    flag = false;
                    ans += ("" + arr[i]);
                    min = arr[i]; // 已存储此值
                    arr[i] = '0'; // 将当前下标值设置为空
                }
            }

            // 从大到小遍历
            char max = '1'; // 存储当前已经使用的最大值
            for (int i = arr.length - 1; i >= 0; i--) {
                // 不动于空值(0),也不等于重复的值
                if (arr[i] != '0' && arr[i] != max) {
                    flag = false;
                    ans += ("" + arr[i]);
                    max = arr[i]; // 已存储此值
                    arr[i] = '0'; // 将当前下标值设置为空
                }
            }

            if (flag) {
                // 返回最终结果
                return ans;
            } else {
                // 继续递归
                return recursion(ans, arr);
            }
        }
    }

    // 桶记发
    class Solution2 {
        public String sortString(String s) {
            int[] num = new int[26];
            for (int i = 0; i < s.length(); i++) {
                num[s.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            while (sb.length() < s.length()) {
                // 升序追加字符
                for (int i = 0; i < 26; i++) {
                    if (num[i] > 0) {
                        sb.append((char) (i + 'a'));
                        num[i]--;
                    }
                }
                // 降序追加
                for (int i = 25; i >= 0; i--) {
                    if (num[i] > 0) {
                        sb.append((char) (i + 'a'));
                        num[i]--;
                    }
                }
            }
            return sb.toString();
        }
    }
}
