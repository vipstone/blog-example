package com.example.structure;

/**
 * 自定义队列(通过循环数组实现)
 */
public class QueueByArray {

    private int[] items;
    private int first;
    private int last;
    private int size;

    public QueueByArray(int capacity) {
        items = new int[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    /**
     * 入列
     * @param val
     */
    public void offer(int val) {
        items[last++] = val;
        // 如果为最后一个元素
        if (last == items.length) last = 0;
        size++;
    }

    /**
     * 移除并返回队首元素
     * @return
     */
    public int poll() {
        int result = Integer.MAX_VALUE;
        if (size > 0) {
            result = items[first++];
            if (first == items.length) first = 0;
            size--;
        }
        return result;
    }

    public static void main(String[] args) {
        QueueByArray queue = new QueueByArray(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
