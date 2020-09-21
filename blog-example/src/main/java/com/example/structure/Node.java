package com.example.structure;

/**
 * 链表的节点
 */
public class Node {
    Object value; // 每个节点的数据
    Node next; // 下一个节点

    public Node(Object value) {
        this(value, null);
    }

    /**
     * 创建新节点
     * @param value 当前节点数据
     * @param next  指向下一个节点（头插法）
     */
    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;
    }
}
