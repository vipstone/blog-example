package com.example.structure;

/**
 * 自定义栈（链表实现方式）
 */
public class QueueByLinked {

    /**
     * 声明链表节点
     * @param <E>
     */
    static class Node<E> {
        E item; // 当前的值

        Node<E> next; // 下一个节点

        Node(E e) {
            this.item = e;
        }
    }

    private Node firstNode; // 队头元素
    private Node lastNode; // 队尾元素
    private int size; // 队列实际存储数量
    private int maxSize; // 队列最大容量

    public QueueByLinked(int maxSize) {
        if (maxSize <= 0) throw new RuntimeException("队列最大容量不能为空");
        // 默认初始化函数
        firstNode = lastNode = new Node(null);
        this.size = 0;
        this.maxSize = maxSize;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 元素入列（到队尾）
     * @param e
     * @return
     */
    public void offer(Object e) {
        // 最大值效验
        if (maxSize <= size) throw new RuntimeException("队列已满");
        Node node = new Node(e);
        lastNode = lastNode.next = node; // 设置最后一个节点和倒数第二个节点的 next
        size++; // 队列数量 +1
    }

    /**
     * 移除队头元素，并返回元素信息
     * @return
     */
    public Node poll() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        size--; // 队列数量 -1
        return firstNode = firstNode.next; // 设置并返回队头元素（第一个节点是 null，当前元素则为 Node.next）
    }

    /**
     * 查询队头元素
     * @return
     */
    public Node peek() {
        if (isEmpty()) throw new RuntimeException("队列为空");
        return firstNode.next;  // 返回队头元素（第一个节点是 null，当前元素则为 Node.next）
    }

    /**
     * 代码测试
     * @param args
     */
    public static void main(String[] args) {
        QueueByLinked queue = new QueueByLinked(10);
        queue.offer("Hello");
        queue.offer("JDK");
        queue.offer("Java");
        System.out.println(queue.poll().item);
        System.out.println(queue.poll().item);
        System.out.println(queue.poll().item);
    }
}
