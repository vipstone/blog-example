package com.example;

/**
 * 死锁示例
 */
public class DeadLockExample {
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "：锁住 obj1。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 尝试获取 obj2
                synchronized (obj2) {
                    System.out.println("1s 后尝试获取 obj2。");
                }
            }
        }).start();
        synchronized (obj2) {
            System.out.println(Thread.currentThread().getName() + "：锁住 obj1。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 尝试获取 obj1
            synchronized (obj1) {
                System.out.println("1s 后尝试获取 obj1。");
            }
        }
    }
}
