package com.example.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class LeetCode225 {
    /**
     * 实现方法一：使用双端队列
     */
    static class MyStack {

        ArrayDeque<Integer> deque;

        public MyStack() {
            deque = new ArrayDeque<>();
        }

        /**
         * 入栈
         */
        public void push(int x) {
            deque.offer(x);
        }

        /**
         * 出栈并返回此元素
         */
        public int pop() {
            return deque.pollLast();
        }

        /**
         * 查询栈顶元素
         */
        public int top() {
            return empty() ? -1 : deque.peekLast();
        }

        /**
         * 判断是否为空
         */
        public boolean empty() {
            return deque.isEmpty();
        }
    }

    /**
     * 实现方法二：使用两个队列
     */
    static class MyStack2 {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack2() {
            queue1 = new LinkedBlockingQueue();
            queue2 = new LinkedBlockingQueue();
        }

        /**
         * 入栈
         */
        public void push(int x) {
            // 1.入列临时队列二
            queue2.offer(x);
            // 2.将队列一的所有元素移动到队列二
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            // 3.队列一和队列二互换
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /**
         * 出栈并返回此元素
         */
        public int pop() {
            return queue1.poll();
        }

        /**
         * 查询栈顶元素
         */
        public int top() {
            return queue1.peek();
        }

        /**
         * 判断是否为空
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    /**
     * 实现方法三：使用一个队列，每次将新入队列之前的元素，重新入列到新加元素之后
     */
    static class MyStack3 {
        Queue<Integer> queue1;

        public MyStack3() {
            queue1 = new LinkedBlockingQueue();
        }

        /**
         * 入栈
         */
        public void push(int x) {
            // 获取原队列长度（要移动的次数）
            int count = queue1.size();
            // 将元素放入队尾
            queue1.offer(x);
            // 将除最后一个元素外，其他的元素重新入队
            for (int i = 0; i < count; i++) {
                System.out.println("for");
                queue1.offer(queue1.poll());
            }
        }

        /**
         * 出栈并返回此元素
         */
        public int pop() {
            return queue1.poll();
        }

        /**
         * 查询栈顶元素
         */
        public int top() {
            return queue1.peek();
        }

        /**
         * 判断是否为空
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    // 测试代码
    public static void main(String[] args) {
        MyStack3 obj = new MyStack3();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);


    }
}
