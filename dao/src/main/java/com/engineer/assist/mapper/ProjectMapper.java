package com.engineer.assist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.engineer.assist.entity.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectInfo> {

}
