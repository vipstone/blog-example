package com.example.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 vs 线程 性能对比
 */
public class ThreadPoolPerformance {

    public static final int maxCount = 1000;

    public static void main(String[] args) throws InterruptedException {
        // 线程测试代码
        ThreadPerformanceTest();

        // 线程池测试代码
        ThreadPoolPerformanceTest();
    }

    /**
     * 线程池性能测试
     */
    private static void ThreadPoolPerformanceTest() throws InterruptedException {
        // 开始时间
        long stime = System.currentTimeMillis();
        // 业务代码
        ThreadPoolExecutor tp = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < maxCount; i++) {
            tp.execute(new PerformanceRunnable());
        }
        tp.shutdown();
        tp.awaitTermination(1, TimeUnit.SECONDS);  // 等待线程池执行完成
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("线程池执行时长：%d 毫秒.", (etime - stime));
        System.out.println();
    }

    /**
     * 线程性能测试
     */
    private static void ThreadPerformanceTest() {
        // 开始时间
        long stime = System.currentTimeMillis();
        for (int i = 0; i < maxCount; i++) {
            Thread td = new Thread(new PerformanceRunnable());
            td.start();
            try {
                td.join(); // 确保线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("线程执行时长：%d 毫秒.", (etime - stime));
        System.out.println();
    }


    static class PerformanceRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < maxCount; i++) {
                long num = i * i + i;
            }
        }
    }


}
