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
}
