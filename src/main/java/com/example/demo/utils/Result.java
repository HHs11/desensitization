package com.example.demo.utils;


import java.io.Serializable;

public class Result<T> implements ResultInterface, Serializable {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    String code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    Object data;
}
