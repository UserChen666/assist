package com.engineer.assist.service.impl;

import com.engineer.assist.dto.ProjectDTO;
import com.engineer.assist.entity.ProjectData;
import com.engineer.assist.mapper.ProjectDataMapper;
import com.engineer.assist.req.ProjectReq;
import com.engineer.assist.result.ProjectResult;
import com.engineer.assist.service.IProjectDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Service
public class ProjectDataServiceImpl extends ServiceImpl<ProjectDataMapper, ProjectData> implements IProjectDataService {
    @Autowired
    private ProjectDataMapper projectDataMapper;

    @Override
    public List<ProjectData> list(ProjectReq projectDTO) {
        return projectDataMapper.list(projectDTO);
    }
}
