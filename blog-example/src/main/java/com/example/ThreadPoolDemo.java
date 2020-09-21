package com.example;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池拒绝策略模拟触发 Demo
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        executor.execute(() -> {
            System.out.println("开始执行任务1");
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1执行结束1");
        });

        executor.execute(() -> {
            System.out.println("开始执行任务2");
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2执行结束");
        });

        executor.execute(() -> {
            System.out.println("开始执行任务3");
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务3执行结束");
        });


    }
}
