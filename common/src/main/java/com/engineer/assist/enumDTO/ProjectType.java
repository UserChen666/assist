package com.engineer.assist.enumDTO;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

public enum ProjectType implements IEnum {

    CAD(0,"CAD"),
    PDF(1,"PDF"),
    WORD(2,"WORD");

    private int code;
    private String name;

    ProjectType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
}
