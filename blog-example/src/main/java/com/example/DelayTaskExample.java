package com.example;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务执行方法汇总
 */
public class DelayTaskExample {
    // 存放定时任务
    private static Map<String, Long> _TaskMap = new HashMap<>();

    public static void main(String[] args) {
        // 1、无限循环实现延迟任务
        System.out.println("程序启动时间：" + LocalDateTime.now());
        // 添加定时任务
        _TaskMap.put("task-1", Instant.now().plusSeconds(3).toEpochMilli()); // 延迟 3s
        // 调用无限循环实现延迟任务
        loopTask();

        // 2、ScheduledExecutorService 实现延迟任务
        System.out.println("程序启动时间：" + LocalDateTime.now());
        scheduledExecutorServiceTask();

        // 3、Netty
        System.out.println("程序启动时间：" + LocalDateTime.now());
        NettyTask();
    }

    /**
     * 基于 Netty 的延迟任务
     */
    private static void NettyTask() {
        // 创建延迟任务实例
        HashedWheelTimer timer = new HashedWheelTimer(3, // 时间间隔
                TimeUnit.SECONDS,
                100); // 时间轮中的槽数
        // 创建一个任务
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务" +
                        " ，执行时间：" + LocalDateTime.now());
            }
        };
        // 将任务添加到延迟队列中
        timer.newTimeout(task, 0, TimeUnit.SECONDS);

    }

    /**
     * ScheduledExecutorService 实现固定频率一直循环执行任务
     */
    public static void scheduledExecutorServiceTask() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("执行任务" +
                                " ，执行时间：" + LocalDateTime.now());
                    }
                },
                2, // 初次执行间隔
                2, // 2s 执行一次
                TimeUnit.SECONDS);
    }

    /**
     * 无限循环实现延迟任务
     */
    public static void loopTask() {
        Long itemLong = 0L;
        while (true) {
            Iterator it = _TaskMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                itemLong = (Long) entry.getValue();
                // 有任务需要执行
                if (Instant.now().toEpochMilli() >= itemLong) {
                    // 延迟任务，业务逻辑执行
                    System.out.println("执行任务：" + entry.getKey() +
                            " ，执行时间：" + LocalDateTime.now());
                    // 删除任务
                    _TaskMap.remove(entry.getKey());
                }
            }
        }
    }
}
