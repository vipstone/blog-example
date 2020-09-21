package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 20 题
 */
public class Valid20 {
    public static void main(String[] args) {
        String s = "(([]))";
        System.out.println(s + " 验证结果：" + isValid(s));
        s = "(([)])";
        System.out.println(s + " 验证结果：" + isValid(s));
    }

    public static boolean isValid2(String s) {
        int len;
        do {
            len = s.length();
            // 消除成双成对的符号
            s = s.replace("()", "").replace("[]", "").
                    replace("{}", "");
        } while (len != s.length()); // 不能再进行替换了，replace 方法没有替换任何字符
        return s.length() == 0;
    }

    public static boolean isValid(String s) {
        int slen = s.length(); // 括号的长度
        if (slen % 2 == 1) { // 括号不是成对出现直接返回 false
            return false;
        }
        // 把所有对比的括号存入 map，对比时用
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        // 定义栈，用于存取括号（辅助比较）
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < slen; i++) { // 循环所有字符
            char c = s.charAt(i);
            if (map.containsKey(c)) { // 为右边的括号，如 ')'、'}' 等
                if (stack.isEmpty() || stack.peek() != map.get(c)) { // 栈为空或括号不匹配
                    return false;
                }
                stack.pop(); // 是一对括号，执行出栈（消除左右括号）
            } else { // 左边括号，直接入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
