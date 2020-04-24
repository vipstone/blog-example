package com.example;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 去除 if/else 代码示例
 * 2020.4.21
 */
public class NoIfElse {

    static enum TypeEnum {
        Name(1), Age(2), Address(3);

        public Integer typeId;

        TypeEnum(Integer typeId) {
            this.typeId = typeId;
        }
    }

    static interface IType {
        public Integer getType();
    }

    static class Name implements IType {
        @Override
        public Integer getType() {
            return 1;
        }
    }

    static class Age implements IType {
        @Override
        public Integer getType() {
            return 2;
        }
    }

    static class Address implements IType {
        @Override
        public Integer getType() {
            return 3;
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        String type = "Name";
        IType itype = (IType) Class.forName("com.example." + type).newInstance();
        Integer typeId = itype.getType();

        // 三目表达式，优化前
        Integer score = 81;
        if (score > 80) {
            score = 100;
        } else {
            score = 60;
        }
        // 三目表达式，优化后
        score = score > 80 ? 100 : 60;


        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        Boolean flag = false;
        Integer result = flag ? (a * b) : c;

    }

    /**
     * 枚举优化
     */
    private static void enumNoElse() {
        Integer typeId = 0;
        String type = "Name";
        if ("Name".equals(type)) {
            typeId = 1;
        } else if ("Age".equals(type)) {
            typeId = 2;
        } else if ("Address".equals(type)) {
            typeId = 3;
        }
        // 优化后
        typeId = TypeEnum.valueOf("Name").typeId;
    }


    /**
     * switch 去除 else
     */
    private static void switchNoElse() {
        // 使用 switch
        Double result = 0d;
        Double n1 = 1d;
        Double n2 = 1d;
        String cmd = "add";
        if (cmd.equals("add")) {
            result = n1 + n2;
        } else if (cmd.equals("subtract")) {
            result = n1 - n2;
        } else if (cmd.equals("multiply")) {
            result = n1 * n2;
        } else if (cmd.equals("divide")) {
            result = n1 / n2;
        } else if (cmd.equals("modulo")) {
            result = n1 % n2;
        }
        // 优化后
        switch (cmd) {
            case "add":
                result = n1 + n2;
                break;
            case "subtract":
                result = n1 - n2;
                break;
            case "multiply":
                result = n1 * n2;
                break;
            case "divide":
                result = n1 / n2;
                break;
            case "modulo":
                result = n1 % n2;
                break;
        }
//        // jdk 14+
//        switch (cmd) {
//            case "add" -> {
//                result = n1 + n2;
//            }
//            case "subtract" -> {
//                result = n1 - n2;
//            }
//            case "multiply" -> {
//                result = n1 * n2;
//            }
//            case "divide" -> {
//                result = n1 / n2;
//            }
//            case "modulo" -> {
//                result = n1 % n2;
//            }
    }

    /**
     * 使用 Optional
     */
    private static void optionalNoElse() {
        // 优化前
        String str = "java";
        if (str == null) {
            System.out.println("Null");
        } else {
            System.out.println(str);
        }
        // 优化后
        Optional<String> opt = Optional.of("java");
        opt.ifPresentOrElse(v -> System.out.println(v), () -> System.out.println("Null"));
        // opt.ifPresentOrElse(System.out::println, () -> System.out.println("Null"));
    }

    /**
     * 使用 return 去掉不必要的 else
     */
    private static void returnNoElse() {
        String str = "java";
        // 优化前
        if (str.equals("java")) {
            // doSomething
        } else {
            return;
        }
        // 优化后
        if (!str.equals("java")) {
            return;
        }
        // doSomething
    }

    /**
     * 使用 Map 集合去除
     */
    private static void mapNoElse() {
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
        // 先判空，不然可能会有问题
        if (!tMap.containsKey(type)) {
            return;
        }
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
