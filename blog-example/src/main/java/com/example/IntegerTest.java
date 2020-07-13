package com.example;

public class IntegerTest {
    public static void main(String[] args) {
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);
        System.out.println(i1 == i2);
        Integer i5 = 558;
        Integer i6 = 558;
        System.out.println((i5 ^ i6) == 0); // 异或
        System.out.println(i5.intValue() == i6.intValue()); // intValue
    }
}
