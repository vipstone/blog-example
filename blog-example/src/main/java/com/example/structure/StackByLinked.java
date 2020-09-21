package com.example.structure;

/**
 * 栈（链表实现）
 */
public class StackByLinked {

    private Node top = null; // 栈顶数据
    private int maxSize = 0; // 栈最大容量
    private int leng = 0; // 栈实际容量

    public StackByLinked(int initSize) throws Exception {
        if (initSize <= 0) {
            throw new Exception("栈容量不能小于等于0");
        }
        top = null;
        maxSize = initSize;
        leng = 0;
    }

    /**
     * 容量是否已满
     * @return
     */
    public boolean isFull() {
        return leng >= maxSize;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return leng <= 0;
    }

    /**
     * 入栈
     * @param val
     * @return
     * @throws Exception
     */
    public boolean push(Object val) throws Exception {
        if (this.isFull()) {
            // 容量已满
            throw new Exception("容量已满");
        }
        top = new Node(val, top); // 存入信息，并将当前节点设置为头节点
        leng++;
        return true;
    }

    /**
     * 出栈（移除）
     * @return
     * @throws Exception
     */
    public Node pop() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("栈为空，无法进行移除操作");
        }
        Node item = top; // 返回当前元素
        top = top.next;
        leng--;
        return item;
    }

    /**
     * 查询栈顶信息
     * @return
     */
    public Node peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("你操作的是一个空栈");
        }
        return top;
    }

    public static void main(String[] args) throws Exception {
        StackByLinked stack = new StackByLinked(10);
        stack.push("Hello");
        stack.push("Java");
        System.out.println(stack.peek().value);
        stack.pop();
        System.out.println(stack.pop().value);
        System.out.println(stack.leng);
    }
}
