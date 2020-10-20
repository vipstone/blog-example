package com.example.leetcode;

import java.util.Stack;

/**
 * 两个栈实现一个队列
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 */
public class Offer09 {
    static class CQueue {

        Stack<Integer> inputStack; // 入栈的容器（添加时操作)
        Stack<Integer> outputStack; // 出栈和查询的栈容器

        public CQueue() {
            inputStack = new Stack();
            outputStack = new Stack();
        }

        // 添加操作
        public void appendTail(int value) {
            inputStack.push(value);
        }

        // 删除操作
        public int deleteHead() {
            if (!outputStack.isEmpty()) {
                // 出栈容器不为空
                return outputStack.pop();
            } else if (!inputStack.isEmpty()) {
                // 入栈 stack 全部转移到出栈 stack
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.isEmpty() ? -1 : outputStack.pop();
        }
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
