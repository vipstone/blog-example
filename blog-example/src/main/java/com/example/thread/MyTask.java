package com.example.thread;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 自定义定时器
 */

class MyTimer implements Comparable<MyTimer> {
    Runnable runnable;
    long timer;

    /**
     * @param runnable
     * @param after    单位秒
     */
    public MyTimer(Runnable runnable, long after) {
        this.runnable = runnable;
        this.timer = Instant.now().getEpochSecond() + after;
    }

    public void run() {
        runnable.run();
    }

    @Override
    public int compareTo(@NotNull MyTimer o) {
        return (int) (this.timer - o.timer);
    }
}

class Worker {

    private Object lock = new Object();

    // 存储定时任务
    private PriorityBlockingQueue<MyTimer> priorityBlockingQueue = new PriorityBlockingQueue();

    // 添加方法
    public void schedule(Runnable runnable, long after) {
        priorityBlockingQueue.put(new MyTimer(runnable, after));
        synchronized (lock) {
            lock.notify();
        }
    }

    public Worker() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                // 拿到第一个任务
                MyTimer myTimer = null;
                try {
                    myTimer = priorityBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (myTimer.timer <= Instant.now().getEpochSecond()) {
                    // 执行任务
                    myTimer.run();
                } else {
                    // 没到任务时间
                    priorityBlockingQueue.put(myTimer);
                    System.out.println("还没到执行时间");
                    try {
                        synchronized (lock) {
                            System.out.println("休眠等待");
                            lock.wait((myTimer.timer - Instant.now().getEpochSecond()) * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }
}

// 调用类
public class MyTask {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                worker.schedule(this, 3);
                System.out.println("执行任务:" + new Date());
            }
        };
        System.out.println("添加任务:" + new Date());
        worker.schedule(runnable, 3);
    }
}
