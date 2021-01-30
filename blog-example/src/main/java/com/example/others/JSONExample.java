package com.example.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
class Animal {
    private int id;
    private String name;
    private int age;
}

/**
 * json 转换示例
 */
public class JSONExample {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        // object to json string
        Animal animal = new Animal();
        animal.setId(1);
        animal.setAge(18);
        animal.setName("小明");
        System.out.println(mapper.writeValueAsString(animal));

        // list to json string
        List<Animal> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Animal item = new Animal();
            item.setId(1);
            item.setAge(18);
            item.setName("小明");
            list.add(item);
        }
        System.out.println(mapper.writeValueAsString(list));

        // json to list
        String jsonstr = "[{\"id\":1,\"name\":\"小明\",\"age\":18},{\"id\":1,\"name\":\"小明\",\"age\":18},{\"id\":1,\"name\":\"小明\",\"age\":18}]";
        List<Animal> list2 = mapper.readValue(jsonstr, new TypeReference<List<Animal>>() {
        });
        System.out.println(list2.get(0).getName());

        // json to object
        jsonstr = "{\"id\":1,\"name\":\"小明22\",\"age\":18}";
        Animal animal2 = mapper.readValue(jsonstr, Animal.class);
        System.out.println(animal2.getName());
    }
}
