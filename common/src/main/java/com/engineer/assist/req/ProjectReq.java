package com.engineer.assist.req;

import com.engineer.assist.entity.ProjectData;
import com.engineer.assist.entity.ProjectInfo;
import lombok.Data;

@Data
public class ProjectReq extends ProjectInfo {
    private int pageNum;
    private int pageSize;
    private int total;
}
