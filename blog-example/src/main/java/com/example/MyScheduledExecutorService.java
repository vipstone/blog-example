package com.example;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyScheduledExecutorService {
    public static void main(String[] args) {
//        errorTest1(); // 任务执行时间长，对其他任务没有影响

        // 创建任务队列
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);
        // 执行任务 1
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("进入 Schedule：" + new Date());
            // 模拟异常
            int num = 8 / 0;
            System.out.println("Run Schedule：" + new Date());
        }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
        // 执行任务 2
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Run Schedule2：" + new Date());
        }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次

    }

    // 任务执行时间长，对其他任务没有影响
    private static void errorTest1() {
        // 创建任务队列
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);
        // 执行任务 1
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("进入 Schedule：" + new Date());
            try {
                // 休眠 5 秒
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Run Schedule：" + new Date());
        }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
        // 执行任务 2
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Run Schedule2：" + new Date());
        }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
    }
}
