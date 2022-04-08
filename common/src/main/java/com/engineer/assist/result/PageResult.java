package com.engineer.assist.result;

import lombok.Data;

import java.util.List;

@Data
public class PageResult <T>{
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> data;
}
