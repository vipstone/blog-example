package com.example.file;

import java.io.*;

class Animal {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class Cat extends Animal implements Serializable {
    private static Long serialVersionUID = 2L;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

public class SerializeParentExample {

    public static void main(String[] args) {
        // 文件地址
        String filePath = "/Users/mac/Downloads/io_test/serialize_parent.txt";
//        // 实例化对象
//        Cat cat = new Cat();
//        cat.setId(1);
//        cat.setAge(3);
//
//        // 序列化
//        serializeTest(filePath, cat);
//        System.out.println("存储文件成功");
        
        // 反序列化
        Cat deCat = (Cat) deSerialize(filePath);
        System.out.println("id:" + deCat.getId());
        System.out.println("name:" + deCat.getName());
        System.out.println("age:" + deCat.getAge());
    }

    /**
     * 反序列化
     * @param filePath 文件地址
     * @return Person
     */
    private static Object deSerialize(String filePath) {
        Object result = null;
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new FileInputStream(filePath))) {
            result = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 序列化
     * @param filePath 文件地址
     */
    private static void serializeTest(String filePath, Object data) {
        // 存储数据
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
