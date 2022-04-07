package com.engineer.assist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engineer.assist.dto.ProjectDTO;
import com.engineer.assist.entity.ProjectData;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.result.ProjectResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Mapper
public interface ProjectDataMapper extends BaseMapper<ProjectData> {

    List<ProjectData> list(ProjectReq projectDTO);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectData record);

    int insertSelective(ProjectData record);

    ProjectData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectData record);

    int updateByPrimaryKey(ProjectData record);
}
