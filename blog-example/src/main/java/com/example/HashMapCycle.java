package com.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMap 循环方式性能对比
 */
public class HashMapCycle {
    static Map<Integer, String> map = new HashMap() {{
        // 添加数据
        for (int i = 0; i < 3; i++) {
            put(i, "val:" + i);
        }
    }};

    public static void main(String[] args) {
        entrySet();
        keySet();
        forEachEntrySet();
        forEachKeySet();
        lambda();
        streamApi();
        parallelStreamApi();

        // 安全的删除
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == 1) {
                // 删除
                System.out.println("del:" + entry.getKey());
                iterator.remove();
            } else {
                System.out.println("show:" + entry.getKey());
            }
        }

        // 根据 map 中的 key 去判断删除
        map.keySet().removeIf(key -> key == 1);
        map.forEach((key, value) -> {
            if (key == 1) {
                System.out.println("del:" + key);
                map.remove(key);
            } else {
                System.out.println("show:" + key);
            }
        });

        // stream api filter
        map.entrySet().stream().filter(m -> 1 != m.getKey()).forEach((entry) -> {
            if (entry.getKey() == 1) {
                System.out.println("del:" + entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }


    public static void entrySet() {
        // 遍历
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    public static void keySet() {
        // 遍历
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }


    public static void forEachEntrySet() {
        // 遍历
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }


    public static void forEachKeySet() {
        // 遍历
        for (Integer key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }


    public static void lambda() {
        // 遍历
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }


    public static void streamApi() {
        // 单线程遍历
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }


    public static void parallelStreamApi() {
        // 多线程遍历
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }
}
