package com.example.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FinallyExample {


    public static void main(String[] args) {
        noFinally();

    }

    private static void noFinally() {
        try {
            System.out.println("我是 try~");
            System.exit(0);
        } catch (Exception e) {
            // do something
        } finally {
            System.out.println("我是 fially~");
        }
    }

    private static void errLog() throws FileNotFoundException {
        // 将标准输出流的信息定位到 log.txt 中
        System.setOut(new PrintStream(new FileOutputStream("log.txt")));
        System.out.println("我是标准输出流");
        System.err.println("我是标准错误输出流");
    }

    private static void execErr() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println(e);
        } finally {
            System.out.println("执行 finally.");
        }
    }

    // finally 无法改变值
    private static int getValue() {
        int num = 1;
        try {

        } finally {
            num++;
        }
        return num;
    }

    // finally 无法改变值[修正]
    private static int getValueByAmend() {
        int num = 1;
        try {
            // do something
        } catch (Exception e) {
            // do something
        } finally {
            num++;
        }
        return num;
    }

    // 不要在 finally 中使用 return
    private static int test() {
        int num = 0;
        try {
            // num=1,此处不返回
            num++;
            return num;
        } catch (Exception e) {
            // do something
        } finally {
            // num=2,返回此值
            num++;
            return num;
        }
    }

    // test 方法的修正版本
    private static int testAmend() {
        int num = 0;
        try {
            num = 1;
        } catch (Exception e) {
            // do something
        } finally {
            // do something
        }
        return num;
    }

}
