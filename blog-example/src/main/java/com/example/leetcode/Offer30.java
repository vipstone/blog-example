package com.example.leetcode;

/**
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 */
public class Offer30 {

    static class MinStack {
        private int[] data; // 栈数据
        private int maxSize; // 最大长度
        private int top; // 栈顶指针（下标）
        private int min; // 最小值

        public MinStack() {
            // 设置默认值
            maxSize = 10000;
            data = new int[maxSize];
            top = -1;
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (min >= x) {
                // 遇到了更小的值，记录原最小值（入栈）
                data[++top] = min;
                min = x;
            }
            // 当前值入栈
            data[++top] = x;
        }

        public void pop() {
            if (min == data[top]) {
                min = data[--top]; // 拿到原最小值，并（将原最小值）出栈
            }
            --top; // 出栈
        }

        public int top() {
            return data[top];
        }

        public int min() {
            return min;
        }
    }

//    static class MinStack {
//        private Stack<Integer> stack = new Stack<>();
//        private int min = Integer.MAX_VALUE;
//
//        public MinStack() {
//
//        }
//
//        public void push(int x) {
//            if (x <= min) {
//                // 遇到了更小的值，记录原最小值（入栈）
//                stack.push(min);
//                min = x;
//            }
//            stack.push(x);
//        }
//
//        public void pop() {
//            if (stack.pop() == min) {
//                min = stack.pop(); // 取出原最小值
//            }
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int min() {
//            return min;
//        }
//    }

    public static void main(String[] args) throws Exception {
        MinStack obj = new MinStack();
        obj.push(-1);
        obj.push(3);
        obj.push(2);
        obj.push(-2);

        System.out.println(obj.min());
        obj.pop();
        System.out.println(obj.min());

    }

}
