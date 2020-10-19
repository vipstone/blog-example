package com.example.structure;

import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class JDKQueue {

    public static void main(String[] args) {
        SynchronousQueue queue = new SynchronousQueue();

        // 入列
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println(new Date() + "，元素入列");
                    queue.put("Data " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        // 出列
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + "，元素出列：" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Viper {
        private int id; // id
        private String name; // 名称
        private int level; // 等级

        public Viper(int id, String name, int level) {
            this.id = id;
            this.name = name;
            this.level = level;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    /**
     * 优先队列示例
     */
    static class PriorityQueueTest {
        public static void main(String[] args) {
            PriorityQueue queue = new PriorityQueue(10, new Comparator<Viper>() {
                @Override
                public int compare(Viper v1, Viper v2) {
                    // 设置优先级规则（倒序，等级越高权限越大）
                    return v2.getLevel() - v1.getLevel();
                }
            });
            // 构建实体类
            Viper v1 = new Viper(1, "Java", 1);
            Viper v2 = new Viper(2, "MySQL", 5);
            Viper v3 = new Viper(3, "Redis", 3);
            // 入列
            queue.offer(v1);
            queue.offer(v2);
            queue.offer(v3);
            while (!queue.isEmpty()) {
                // 遍历名称
                Viper item = (Viper) queue.poll();
                System.out.println("Name:" + item.getName() +
                        " Level:" + item.getLevel());
            }
        }
    }


    /**
     * 双端队列示例
     */
    static class LinkedBlockingDequeTest {
        public static void main(String[] args) {
            // 创建一个双端队列
            LinkedBlockingDeque deque = new LinkedBlockingDeque();
            deque.offer("offer"); // 插入首个元素
            deque.offerFirst("offerFirst"); // 队头插入元素
            deque.offerLast("offerLast"); // 队尾插入元素
            while (!deque.isEmpty()) {
                // 从头遍历打印
                System.out.println(deque.poll());
            }
        }
    }

    /**
     * 普通队列示例
     */
    static class LinkedBlockingQueueTest {
        public static void main(String[] args) {
            LinkedBlockingQueue queue = new LinkedBlockingQueue();
            queue.offer("Hello");
            queue.offer("Java");
            queue.offer("中文社群");
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
        }
    }

    /**
     * 阻塞队列示例
     */
    static class BlockingTest {
        public static void main(String[] args) throws InterruptedException {
            // 创建一个长度为 5 的阻塞队列
            ArrayBlockingQueue q1 = new ArrayBlockingQueue(5);
            // 新创建一个线程执行入列
            new Thread(() -> {
                // 循环 10 次
                for (int i = 0; i < 10; i++) {
                    try {
                        q1.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date() + " | ArrayBlockingQueue Size:" + q1.size());
                }
                System.out.println(new Date() + " | For End.");
            }).start();

            // 新创建一个线程执行出列
            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        // 休眠 1S
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!q1.isEmpty()) {
                        try {
                            q1.take(); // 出列
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
