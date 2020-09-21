package com.example.structure;

public class MyStack<E> {
    private Object[] value = null; // 栈存储容器
    private int top = -1; // 栈顶（的指针）
    private int maxSize = 0; // 栈容量

    // 构造函数（初始化默认容量）
    MyStack() {
        this.maxSize = 16;
    }

    // 有参构造函数
    MyStack(int initSize) throws Exception {
        if (initSize <= 0) {
            throw new Exception("栈容量必须大于 0");
        } else {
            value = new Object[initSize];
            maxSize = initSize;
            top = -1;
        }
    }

    // 数据添加（入栈）
    public boolean push(E e) throws Exception {
        if (maxSize - 1 == top) {
            throw new Exception("入栈失败，栈已满");
        } else {
            value[++top] = e;
            return true;
        }
    }

    // 数据移除（出栈）
    public E pop() throws Exception {
        if (top <= -1) {
            throw new Exception("移除失败，栈中已无数据");
        } else {
            return (E) value[top--];
        }
    }

    // 数据查询
    public E peep() throws Exception {
        if (top <= -1) {
            throw new Exception("移除失败，栈中已无数据");
        } else {
            return (E) value[top];
        }
    }

    // 代码测试
    public static void main(String[] args) throws Exception {
        MyStack stack = new MyStack(10);
        stack.push("Hello");
        stack.push("Java");
        System.out.println(stack.peep());
        stack.pop();
        System.out.println(stack.pop());
    }
}
