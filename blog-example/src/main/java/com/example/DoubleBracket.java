package com.example;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 双花括号初始化示例
 */
public class DoubleBracket {
    private static String userName = "磊哥";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 持有外部对象证明代码
        Map map = new DoubleBracket().createMap();
        // 获取一个类的所有字段
        Field field = map.getClass().getDeclaredField("this$0");
        // 设置允许方法私有的 private 修饰的变量
        field.setAccessible(true);
        System.out.println(field.get(map).getClass());
        createMap();

        // Java 8 Stream API
        List<String> list = Stream.of("Java", "Redis").collect(Collectors.toList());
        list.forEach(item -> {
            System.out.println("value:" + item);
        });

//        // Java 9 集合工程
//        List<String> list2 = List.of("Java", "Redis");
//        list2.forEach(item -> {
//            System.out.println("value:" + item);
//        });
//
//        Map map = Map.of("map1", "Java", "map2", "Redis");
    }

    public static Map createMap() {
        Map map = new HashMap() {{
            put("map1", "value1");
            put("map2", "value2");
            put("map3", "value3");
        }};
        return map;
    }
}
