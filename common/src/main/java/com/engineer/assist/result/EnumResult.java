package com.engineer.assist.result;

import lombok.Data;

import java.util.List;

@Data
public class EnumResult{
    private List<PairView<Integer, String>> projectTypes;
}
