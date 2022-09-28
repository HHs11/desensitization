package com.example.demo.pojo;

import com.example.demo.aop.Desensitized;
import lombok.Data;

@Data
public class Student {

    @Desensitized(method = "desensitizedName")
    String name;
    String number;
    @Desensitized(method = "desensitizedPhoneNumber")
    String phone;
    @Desensitized(method = "desensitizedAddressV2")
    String address;

    Book book;
}
