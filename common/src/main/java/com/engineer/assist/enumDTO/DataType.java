package com.engineer.assist.enumDTO;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static DataType getByName(int i){
        List<DataType> collect = Arrays.stream(DataType.values()).filter(k -> k.code == i).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(collect)) {
            return null;
        }

        return collect.get(0);
    }
}
