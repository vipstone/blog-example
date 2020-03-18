package com.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class RandomExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        // Random 多线程执行
        for (int i = 0; i < 10; i++) {
            // 创建多个线程执行并打印随机数
            service.submit(() -> {
                Random random = new Random();
                System.out.println(Thread.currentThread().getName() + ":" + random.nextInt(10));
            });
        }

        // ThreadLocalRandom 多线程执行
        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                System.out.println(Thread.currentThread().getName() + ":" + threadLocalRandom.nextInt(10));
            });
        }

//        ThreadLocalRandomErrExample(); // ThreadLocalRandom 错误使用
    }

    /**
     * ThreadLocalRandom 错误使用
     */
    private static void ThreadLocalRandomErrExample() {
        // 声明多线程
        ExecutorService service = Executors.newCachedThreadPool();
        // 共享 ThreadLocalRandom
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            // 多线程执行随机数并打印结果
            service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocalRandom.nextInt(10));
            });
        }
    }


}
