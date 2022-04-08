package com.engineer.assist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engineer.assist.entity.ProjectInfo;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.result.ProjectResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    List<ProjectResult> listForSearch(ProjectReq projectDTO);
}