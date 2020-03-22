package com.example;

/**
 * Java 14 新特性演示
 */
public class Java14Example {
    public static void main(String[] args) {
        instanceofTest(); // instanceof 特性演示
        switchTest(); // switch 特性演示
        recordTest(); // records 特性演示
    }

    /**
     * Records 特性演示
     */
    private static void recordTest() {
        // 记录类型
        record People(Integer id, String name, Integer age) {
        }
        // 实例化
        People people = new People(1, "老王", 18);
        // 输出属性 name
        System.out.println(people.name);
    }

    /**
     * switch 特性演示
     */
    private static void switchTest() {
        // 旧写法
        switch ("java") {
            case "java":
            case "jdk":
                System.out.println("This is Java.");
                break;
            default:
                System.out.println("default");
                break;
        }
        // 新写法
        switch ("java") {
            case "java", "jdk" -> System.out.println("This is Java.");
            default -> System.out.println("default");
        }
    }

    /**
     * instanceof 特性演示
     */
    private static void instanceofTest() {
        Object obj = "Java 中文社群";
        // 旧写法
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println(s);
        }
        // 新写法
        if (obj instanceof String s) {
            System.out.println(s);
        }
    }
}
