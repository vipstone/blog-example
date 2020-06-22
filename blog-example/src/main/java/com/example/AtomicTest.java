package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private final static int maxSize = 1000000;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < maxSize; i++) {
                atomicInteger.incrementAndGet();
            }
        });
        thread.start();

        for (int i = 0; i < maxSize; i++) {
            atomicInteger.decrementAndGet();
        }

        while (thread.isAlive()) {
        }
        System.out.println("执行完成，atomicInteger=" + atomicInteger);

    }
}
