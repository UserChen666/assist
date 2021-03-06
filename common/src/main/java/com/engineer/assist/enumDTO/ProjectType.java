package com.engineer.assist.enumDTO;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ProjectType{

    CAD(0,"CAD"),
    PDF(1,"PDF"),
    WORD(2,"WORD");

    private int code;

    @EnumValue
    @JsonValue
    private String name;

    ProjectType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
