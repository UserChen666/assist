package com.engineer.assist.service;

import com.engineer.assist.entity.ProjectCategoryRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
public interface IProjectCategoryRelService extends IService<ProjectCategoryRel> {

    void deleteByProjectId(Integer projectId);

    List<ProjectCategoryRel> selectByProjectId(Integer id);

    List<Integer> getCategoryByProjectIds(Integer id);
}
