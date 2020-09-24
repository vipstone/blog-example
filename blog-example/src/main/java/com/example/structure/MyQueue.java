package com.example.structure;

public class MyQueue<E> {

    private Object[] queue; // 存储容器
    private int head; // 头部指针
    private int tail; // 尾部指正
    private int size; // 队列长度
    private int maxSize; // 最大容量

    public MyQueue() {
        // 初始化容量
        this.maxSize = 10;
        this.head = 0;
        this.tail = -1;
        this.size = 0;
        this.queue = new Object[this.maxSize];
    }

    public MyQueue(int initSize) {
        this.maxSize = initSize;
        this.head = 0;
        this.tail = -1;
        this.size = 0;
        this.queue = new Object[this.maxSize];
    }

    /**
     * 查询队列首个内容
     * @return
     * @throws Exception
     */
    public E peek() throws Exception {
        if (size == 0) {
            throw new Exception("队列中暂无数据");
        }
        return (E) this.queue[this.head];
    }

    /**
     * 添加信息（列尾）
     * @param e
     * @return
     */
    public boolean offer(E e) throws Exception {
        if (tail >= (maxSize - 1)) {
            throw new Exception("添加失败，队列已满");
        }
        this.queue[++tail] = e;
        size++;
        return true;
    }

    /**
     * 删除数据（列头）
     * @return
     */
    public E poll() throws Exception {
        if (size == 0) {
            throw new Exception("删除失败，队列为空");
        }
        size--;
        return (E) this.queue[head++];
    }

    // 代码测试
    public static void main(String[] args) throws Exception {
        MyQueue queue = new MyQueue();
        queue.offer("Hello");
        queue.offer("Java");
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.poll());
    }
}
