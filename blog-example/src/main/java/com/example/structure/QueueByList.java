package com.example.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义队列（List方式）
 */
public class QueueByList<E> {

    private List value; // 队列存储容器

    public QueueByList() {
        // 初始化
        value = new ArrayList();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return value.size() == 0;
    }

    /**
     * 元素入列（到队尾）
     * @param e
     * @return
     */
    public void offer(Object e) {
        value.add(e);
    }

    /**
     * 移除队头元素，并返回元素信息
     * @return
     */
    public E poll() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        E item = (E) value.get(0);
        value.remove(0);
        return item;
    }

    /**
     * 查询队头元素
     * @return
     */
    public E peek() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return (E) value.get(0);
    }

    /**
     * 代码测试
     * @param args
     */
    public static void main(String[] args) {
        QueueByList queue = new QueueByList();
        queue.offer("Hello");
        queue.offer("JDK");
        queue.offer("Java");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
