package com.engineer.assist.enumDTO;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType{

    CAD(0,"CAD"),
    PDF(1,"PDF"),
    WORD(2,"WORD");

    private int code;

    @EnumValue
    @JsonValue
    private String name;

    DataType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
