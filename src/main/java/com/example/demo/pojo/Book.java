package com.example.demo.pojo;

import com.example.demo.aop.Desensitized;
import lombok.Data;

@Data
public class Book {

    Integer id;

    String color;

    @Desensitized(method = "desensitizedPhoneNumber")
    String carId;
}
