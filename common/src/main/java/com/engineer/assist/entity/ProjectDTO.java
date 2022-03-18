package com.engineer.assist.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    ProjectInfo projectInfo;
    ProjectData projectData;
    List<Integer> categoryIds;
    List<Category> categories;
    public ProjectInfo getProject(){
        return projectInfo;
    }
}
