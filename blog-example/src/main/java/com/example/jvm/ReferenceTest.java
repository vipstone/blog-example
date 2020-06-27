package com.example.jvm;

/**
 * Java 对象定位|句柄 vs 直接指针
 */
public class ReferenceTest {
    Bus myBus = new Bus("Java中文社群", "蓝色");
}


class Car {

}

class Bus extends Car {
    private String code;
    private String color;

    Bus(String code, String color) {
        this.code = code;
        this.color = color;
    }
}