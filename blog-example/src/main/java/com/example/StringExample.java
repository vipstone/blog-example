package com.example;

public class StringExample {
    public static void main(String[] args) {
//        // 引用对比
//        refCompare();

        String s1 = "abc";
        String s2 = "ab" + "c";
        String s3 = "a" + "b" + "c";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }

    /**
     * 引用对比
     */
    private static void refCompare() {
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        String s4 = new String("Java");
        System.out.println(s1 == s2);
        System.out.println(s3 == s4);
    }
}
