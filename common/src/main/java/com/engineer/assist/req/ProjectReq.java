package com.engineer.assist.req;

import com.engineer.assist.entity.ProjectData;
import lombok.Data;

@Data
public class ProjectReq extends ProjectData {
    private int pageNum;
    private int pageSize;
}
