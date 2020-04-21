package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * 去除 if/else 代码示例
 * 2020.4.21
 */
public class NoIfElse {
    public static void main(String[] args) {


    }

    /**
     * 使用 return 去掉不必要的 else
     */
    private static void returnNoIf() {
        String str = "name";
        // 优化前
        if (str.equals("name")) {
            // doSomething
        } else {
            return;
        }
        // 优化后
        if (!str.equals("name")) {
            return;
        }
        // doSomething
    }

    /**
     * 使用 Map 集合去除
     */
    private static void mapNoIf() {
        Integer type = 3;
        String tName = null;

        // 使用 Map 集合
        // 优化前
        if (type == 1) {
            tName = "name";
        } else if (type == 2) {
            tName = "id";
        } else if (type == 3) {
            tName = "mobile";
        }
        // 优化后
        Map<Integer, String> tMap = new HashMap<>();
        tMap.put(1, "name");
        tMap.put(2, "id");
        tMap.put(3, "mobile");
        tName = tMap.get(type);
    }


    /**
     * 多个 if 伪代码
     */
    private static void multiIf() {
        String str = "Java中文社群";
        if (str.equals("Java")) {
            if (str.equals("Redis")) {
                if (str.equals("Golang")) {
                    if (str.equals("SqlServer")) {
                        if (str.equals("C#")) {
                            if (str.equals("Nodejs")) {
                                if (str.equals("MySQL")) {
                                    System.out.println("中文");
                                } else {
                                    System.out.println("中文");
                                }
                            } else {
                                System.out.println("中文");
                            }
                        } else {
                            System.out.println("社群");
                        }
                    } else {
                        System.out.println("社群");
                    }
                } else {
                    System.out.println("社群");
                }
            } else {
                System.out.println("社群");
            }
        } else {
            System.out.println("社群");
        }
    }

}
