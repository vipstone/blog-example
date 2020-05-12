package com.example.optimize;

public class SwitchOptimizeByString {
    static String _STR = "Java中文社群";

    public static void main(String[] args) {
//        switchTest();
//        ifTest();
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
//        System.out.println("oracle".hashCode());
//        System.out.println("redis".hashCode());
//        System.out.println("mq".hashCode());
//        System.out.println("kafka".hashCode());
//        System.out.println("rabbitmq".hashCode());
    }


    public static void switchTest() {
        String s1;
        switch (_STR) {
            case "java":
                s1 = "java";
                break;
            case "mysql":
                s1 = "mysql";
                break;
            case "oracle":
                s1 = "oracle";
                break;
            case "redis":
                s1 = "redis";
                break;
            case "mq":
                s1 = "mq";
                break;
            case "kafka":
                s1 = "kafka";
                break;
            case "rabbitmq":
                s1 = "rabbitmq";
                break;
            default:
                s1 = "default";
                break;
        }
    }

    public static void ifTest() {
        String s1;
        if ("java".equals(_STR)) {
            s1 = "java";
        } else if ("mysql".equals(_STR)) {
            s1 = "mysql";
        } else if ("oracle".equals(_STR)) {
            s1 = "oracle";
        } else if ("redis".equals(_STR)) {
            s1 = "redis";
        } else if ("mq".equals(_STR)) {
            s1 = "mq";
        } else if ("kafka".equals(_STR)) {
            s1 = "kafka";
        } else if ("rabbitmq".equals(_STR)) {
            s1 = "rabbitmq";
        } else {
            s1 = "default";
        }
    }
}
