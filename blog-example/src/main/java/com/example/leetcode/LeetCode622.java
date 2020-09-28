package com.example.leetcode;

/**
 * 622. 设计循环队列
 * https://leetcode-cn.com/problems/design-circular-queue/
 */
public class LeetCode622 {

    static class MyCircularQueue {
        private int[] data; // 存储队列的容器
        private int size; // 队列实际大小
        private int maxSize; // 队列总大小
        private int top; // 队头指针（下标）

        /**
         * 初始化参数
         */
        public MyCircularQueue(int k) {
            if (k <= 0) throw new RuntimeException("队列长度不能小于0");
            data = new int[k];
            this.maxSize = k;
            size = top = 0;
        }

        /**
         * 向循环队列插入一个元素。如果成功插入则返回真
         */
        public boolean enQueue(int value) {
            if (isFull()) return false;
            data[(top + size) % maxSize] = value; // 存入数据
            size++; // 数量 +1
            return true;
        }

        /**
         * 从循环队列中删除一个元素。如果成功删除则返回真
         */
        public boolean deQueue() {
            if (isEmpty()) return false;
            top++;
            if (top == maxSize) top = 0;
            size--;
            return true;
        }

        /**
         * 从队首获取元素。如果队列为空，返回 -1
         */
        public int Front() {
            if (isEmpty()) return -1;
            return data[top];
        }

        /**
         * 获取队尾元素。如果队列为空，返回 -1
         */
        public int Rear() {
            if (isEmpty()) return -1;
            return data[(top + size - 1) % maxSize];
        }

        /**
         * 检查循环队列是否为空
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 检查循环队列是否已满
         */
        public boolean isFull() {
            return size == maxSize;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(8); // 设置长度为 3
        circularQueue.enQueue(3);
        circularQueue.enQueue(9);
        circularQueue.enQueue(5);
        circularQueue.enQueue(0);
        circularQueue.deQueue();
        circularQueue.deQueue();
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.Rear());
    }
}
