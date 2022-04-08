package com.engineer.assist.service.impl;

import com.engineer.assist.entity.ProjectCategoryRel;
import com.engineer.assist.mapper.ProjectCategoryRelMapper;
import com.engineer.assist.service.IProjectCategoryRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Service
public class ProjectCategoryRelServiceImpl extends ServiceImpl<ProjectCategoryRelMapper, ProjectCategoryRel> implements IProjectCategoryRelService {

    @Override
    public void deleteByProjectId(Integer projectId) {
        ProjectCategoryRelMapper baseMapper = this.baseMapper;
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("project_id",projectId);

        baseMapper.deleteByMap(param);
    }
}
