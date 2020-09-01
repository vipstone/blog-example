package com.example;

/**
 * 引用传递 VS 值传递
 */
public class PassByValue {

    public static void main(String[] args) {
        int age = 18;
        System.out.println("调用方法前：" + age);
        intTest(age);
        System.out.println("调用方法后：" + age);

        char[] name = {'磊', '哥'};
        System.out.println("调用方法前：" + new String(name));
        paramTest(name);
        System.out.println("调用方法后：" + new String(name));

    }

    private static void intTest(int age) {
        age = 30;
        System.out.println("方法中修改为：" + age);
    }

    private static void paramTest(char[] n) {
        n = new char[2]; // 添加此行代码
        n[1] = '神';
        System.out.println("方法中修改为：" + new String(n));
    }
}
