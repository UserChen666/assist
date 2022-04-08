package com.engineer.assist.req;

import com.engineer.assist.entity.ProjectInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProjectReq extends ProjectInfo {
    private int pageNum;
    private int pageSize;
    private int total;
    private List<Integer> categoryIds;
}
