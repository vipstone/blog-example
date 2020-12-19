package com.example.thread;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 线程池 N 种创建方式演示
 */
public class CreateThreadPoolExample {

    public static void fixedThreadPool() {
        // 创建 2 个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行,线程:" +
                        Thread.currentThread().getName());
            }
        };

        // 线程池执行任务(一次添加 4 个任务)
        // 执行任务的方法有两种:submit 和 execute
        threadPool.submit(runnable);  // 执行方式 1:submit
        threadPool.execute(runnable); // 执行方式 2:execute
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    public static void fixedThreadPool2() {
        // 创建 2 个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        // 执行任务
        threadPool.execute(() -> {
            System.out.println("任务被执行,线程:" +
                    Thread.currentThread().getName());
        });
    }

    public static void cachedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
    }

    public static void singleThreadExecutor() {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
    }

    public static void scheduledThreadPool() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时执行任务(1s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }, 1, TimeUnit.SECONDS);
    }

    public static void SingleThreadScheduledExecutor() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        // 添加定时执行任务(2s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }, 2, TimeUnit.SECONDS);
    }

    public static void workStealingPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {
        }
    }

    public static void myThreadPoolExecutor() {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 任务的具体方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行,执行时间:" + new Date() +
                        " 执行线程:" + Thread.currentThread().getName());
                try {
                    // 等待 1s
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t = new Thread(r, "自定义名称" + r.hashCode());
                return t;
            }
        };

        // 创建线程
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2,
                100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), threadFactory,
                new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 4; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println("执行任务:" + index + " name:" + Thread.currentThread().getName());
            });
        }

//        // 添加并执行 4 个任务
//        threadPool.execute(runnable);
//        threadPool.execute(runnable);
//        threadPool.execute(runnable);
//        threadPool.execute(runnable);


//        Future<Integer> submit = threadPool.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                int result = new Random().nextInt(10);
//                System.out.println("得到随机数~");
//                return result;
//            }
//        });
//        System.out.println("得到结果:" + submit.get());

    }


}
