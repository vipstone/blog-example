package com.example.thread;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 工厂类,消费者和生产者通过调用工厂类实现生产/消费功能.
 */
class Factory {
    private int[] items = new int[1];   // 数据存储容器(为了演示方便,设置容量最多存储 1 个元素)
    private int size = 0;               // 实际存储大小

    /**
     * 生产方法
     * @throws InterruptedException
     */
    public synchronized void put() throws InterruptedException {
        // 循环生产数据
        do {
            while (size == items.length) { // 注意不能是 if 判断
                // 存储的容量已经满了,阻塞等待消费者消费之后唤醒
                System.out.println(Thread.currentThread().getName() + " 进入阻塞");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
            System.out.println(Thread.currentThread().getName() + " 开始工作");
            items[0] = 1; // 为了方便演示,设置固定值
            size++;
            System.out.println(Thread.currentThread().getName() + " 完成工作");
            // 唤醒所有线程
            this.notifyAll();
        } while (true);
    }

    /**
     * 消费方法
     * @throws InterruptedException
     */
    public synchronized void take() throws InterruptedException {
        // 循环消费数据
        do {
            while (size == 0) {
                // 生产者没有数据,阻塞等待
                System.out.println(Thread.currentThread().getName() + " 进入阻塞(消费者)");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒(消费者)");
            }
            System.out.println("消费者工作~");
            size--;
            // 唤醒所有线程
            this.notifyAll();
        } while (true);
    }
}

class FactoryByCondition {
    private int[] items = new int[1];       // 数据存储容器(为了演示方便,设置容量最多存储 1 个元素)
    private int size = 0;                   // 实际存储大小
    // 创建 Condition 对象
    private Lock lock = new ReentrantLock();
    // 生产者的锁
    private Condition producerCondition = lock.newCondition();
    // 消费者的锁
    private Condition consumerCondition = lock.newCondition();

    /**
     * 生产方法
     * @throws InterruptedException
     */
    public void put() throws InterruptedException {
        // 循环生产数据
        do {
            lock.lock();
            while (size == items.length) { // 注意不能是 if 判断
                // 生产者进入等待
                System.out.println(Thread.currentThread().getName() + " 进入阻塞");
                producerCondition.await();
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
            System.out.println(Thread.currentThread().getName() + " 开始工作");
            items[0] = 1; // 为了方便演示,设置固定值
            size++;
            System.out.println(Thread.currentThread().getName() + " 完成工作");
            // 唤醒消费者
            consumerCondition.signal();
            try {
            } finally {
                lock.unlock();
            }
        } while (true);
    }

    /**
     * 消费方法
     * @throws InterruptedException
     */
    public void take() throws InterruptedException {
        // 循环消费数据
        do {
            lock.lock();
            while (size == 0) {
                // 消费者阻塞等待
                consumerCondition.await();
            }
            System.out.println("消费者工作~");
            size--;
            // 唤醒生产者
            producerCondition.signal();
            try {
            } finally {
                lock.unlock();
            }
        } while (true);
    }

}

public class NotifyDemo {

    public static void main(String[] args) {


        Factory factory = new Factory();
//        FactoryByCondition factory = new FactoryByCondition();

        // 生产者
        Thread producer = new Thread(() -> {
            try {
                factory.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者");
        producer.start();


        // 生产者 2
        Thread producer2 = new Thread(() -> {
            try {
                factory.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者2");
        producer2.start();

        // 消费者
        Thread consumer = new Thread(() -> {
            try {
                factory.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者");
        consumer.start();


//        doCondition();
//        doLockSupport();
//        doWait();
//        NotifyDemo notifyDemo = new NotifyDemo();
//        notifyDemo.doWait2();
    }

    private void doWait2() {

        Thread t1 = new Thread(() -> {
            synchronized (this) {
                System.out.println("线程进入等待状态");
                try {
                    // 线程进入等待状态
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程执行完");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (this) {
                try {
                    // 休眠一秒钟
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("唤醒线程");
                    // 执行唤醒线程操作
                    this.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

    }

    private static void doWait() {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            // 加锁
            lock.lock();
            System.out.println("线程进入等待状态");
            try {
                // 线程进入等待状态
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
            System.out.println("线程执行完");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            // 加锁
            lock.lock();
            try {
                // 休眠一秒钟
                TimeUnit.SECONDS.sleep(1);
                System.out.println("唤醒线程");
                // 执行唤醒线程操作
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        });
        t2.start();


    }

    private static void doCondition() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            System.out.println("线程一进入等待状态");
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("线程一执行完成");
        });
        t1.start();

        Thread t3 = new Thread(() -> {
            System.out.println("线程三进入等待状态");
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("线程三执行完成");
        });
        t3.start();

        Thread t2 = new Thread(() -> {
            System.out.print("输入任意字符唤醒线程:");
            Scanner scanner = new Scanner(System.in);
            scanner.next();
            lock.lock();
            condition.signal();
//            condition.signalAll();
            try {
            } finally {
                lock.unlock();
            }
        });
        t2.start();
    }

    private static void doLockSupport() {
        Thread t1 = new Thread(() -> {
            System.out.println("线程一进入等待状态");
            // 当前线程进入等待状态
            LockSupport.park();
            System.out.println("线程一执行完成");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.print("输入任意字符唤醒线程:");
            Scanner scanner = new Scanner(System.in);
            // 执行此行,表示已经输入了字符
            scanner.next();
            // 唤醒线程 t1
            LockSupport.unpark(t1);
        });
        t2.start();
    }

}
