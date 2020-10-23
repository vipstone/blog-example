package com.example.leetcode;

import java.util.Stack;

/**
 * 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class LeetCode150 {

    class Solution {

        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            for (String s : tokens) {
                switch (s) {
                    case "+":
                        // 出栈两个数，进行相加
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        // 出栈两个数，进行相减
                        // 注意：是 i2-i1，不是 i1-i2（前面的减后面的，栈相反，因此是 i2-i1）
                        int i1 = stack.pop();
                        int i2 = stack.pop();
                        stack.push(i2 - i1);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        int s1 = stack.pop();
                        int s2 = stack.pop();
                        stack.push(s2 / s1);
                        break;
                    default:
                        // 普通的数组，直接入栈
                        stack.push(Integer.parseInt(s));
                        break;
                }
            }
            return stack.pop();
        }
    }
}
