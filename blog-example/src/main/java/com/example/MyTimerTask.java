package com.example;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 Timer
 */
public class MyTimerTask {
    public static void main(String[] args) {
        normal(); // 正常调用
        errorDemo1(); // 错误示例 1：执行频率相互影响
        errorDemo2(); // 错误示例 2：异常任务相互影响
    }

    // 错误示例 2：异常任务相互影响
    private static void errorDemo2() {
        // 定义任务 1
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("进入 timerTask 1：" + new Date());
                // 模拟异常
                int num = 8 / 0;
                System.out.println("Run timerTask 1：" + new Date());
            }
        };
        // 定义任务 2
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Run timerTask 2：" + new Date());
            }
        };
        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
        timer.schedule(timerTask2, 1000, 3000);
    }

    // 错误示例 1：执行频率相互影响
    private static void errorDemo1() {
        // 定义任务 1
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("进入 timerTask 1：" + new Date());
                try {
                    // 休眠 5 秒
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Run timerTask 1：" + new Date());
            }
        };

        // 定义任务 2
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Run timerTask 2：" + new Date());
            }
        };

        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
        timer.schedule(timerTask2, 1000, 3000);
    }

    // 正常调用 Timer
    private static void normal() {
        // 定义一个任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("run timerTask：" + new Date());
            }
        };
        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
    }
}
