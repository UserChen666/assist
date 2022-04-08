package com.engineer.assist.dto;

import com.engineer.assist.entity.ProjectData;
import com.engineer.assist.entity.ProjectFileRel;
import com.engineer.assist.entity.ProjectInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO extends Page{
    ProjectInfo projectInfo;
    ProjectData projectData;
    List<Integer> categoryIds;
    public ProjectInfo getProject(){
        return projectInfo;
    }
    private List<ProjectFileRel> files;
}
