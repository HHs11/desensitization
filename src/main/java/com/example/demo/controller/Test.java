package com.example.demo.controller;

import com.example.demo.aop.Desensitized;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.Student;
import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Desensitized()
    @RequestMapping("/test")
    Result test() {
        Student student = new Student();
        student.setName("黄海松");
        student.setNumber("1521");
        student.setPhone("15095897110");
        student.setAddress("重庆市渝北区新牌坊2东2-5");
        Book book = new Book();
        book.setCarId("15095897110");
        student.setBook(book);

        Result result = new Result();
        result.setCode("200");
        result.setData(student);
        return result;
    }
}
