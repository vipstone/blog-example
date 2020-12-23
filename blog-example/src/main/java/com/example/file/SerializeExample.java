package com.example.file;


import java.io.*;

class Person implements Serializable {
    private static final long serialVersionUID = 4405428051280290987L;
    private int id;
    private String name;
    private int age;

    public static int count = 0;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class SerializeExample {
    public static void main(String[] args) {
        // 文件地址
        String filePath = "/Users/mac/Downloads/io_test/serialize.txt";

        // 实例化的对象
        Person person = new Person();
        person.setId(1);
        person.setName("磊哥");
        person.setAge(18);
        // 序列化
        serializeTest(filePath, person);
        System.out.println("存储文件成功");
        // 序列化之后修改(静态)变量
        person.count = 1;
        person.setName("磊叔");

        // 反序列化
        Person dePerson = (Person) deSerialize(filePath);
        System.out.println("name:" + dePerson.getName());
        System.out.println("count:" + dePerson.count);
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
