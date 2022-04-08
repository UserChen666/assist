package com.engineer.assist.service;

import com.engineer.assist.entity.ProjectData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.engineer.assist.req.ProjectReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
public interface IProjectDataService extends IService<ProjectData> {

    ProjectData getByProjectId(Integer id);
}
