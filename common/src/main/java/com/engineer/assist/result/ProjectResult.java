package com.engineer.assist.result;

import com.engineer.assist.entity.ProjectInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProjectResult extends ProjectInfo {
    private List<Integer> categoryIds;
    private String pic;
}
