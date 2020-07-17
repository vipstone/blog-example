package com.example.thread;

/**
 * wait 示例提交
 */
public class WaitDemo {
    private static Object locker = new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitDemo waitDemo = new WaitDemo();

        // 启动新线程，防止主线程被休眠
        new Thread(() -> {
            try {
                waitDemo.doWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        // 启动新线程，防止主线程被休眠
//        new Thread(() -> {
//            synchronized (locker) {
//                try {
//                    System.out.println("sleep start.");
//                    Thread.sleep(1000);
//                    System.out.println("sleep end.");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Thread.sleep(200);
        waitDemo.doNotify();


    }

    /**
     * 执行 wait()
     */
    private void doWait() throws InterruptedException {
        synchronized (locker) {
            System.out.println("wait start.");
            while (true) {
                locker.wait();
            }
//            System.out.println("wait end.");
        }
    }

    /**
     * 执行 notify()
     */
    private void doNotify() {
        synchronized (locker) {
            System.out.println("notify start.");
            locker.notify();
            System.out.println("notify end.");
        }
    }
}
