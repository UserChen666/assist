package com.engineer.assist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.Category;
import com.engineer.assist.entity.CategoryDTO;
import com.engineer.assist.entity.ProjectCategoryRel;
import com.engineer.assist.mapper.CategoryMapper;
import com.engineer.assist.service.ICategoryService;
import com.engineer.assist.service.IProjectCategoryRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    IProjectCategoryRelService iProjectCategoryRelService;

    @Override
    public List<Category> list(Category category) {
        List<Category> list = lambdaQuery().like(Category::getCategoryName, category).eq(Category::getIsActive, category.getIsActive()).eq(Category::getParentId, category.getParentId()).list();
        return list;
    }

    @Override
    public Boolean create(CategoryDTO categoryDTO) {
        Category category = categoryDTO.getCategory();
        boolean save = save(category);
        List<Integer> projectIds = categoryDTO.getProjectIds();
        if (projectIds.isEmpty()) return save;
        projectIds.forEach(item -> {
            ProjectCategoryRel rel=new ProjectCategoryRel();
            rel.setCategoryId(category.getId());
            rel.setProjectId(item);
            iProjectCategoryRelService.save(rel);
        });
        return save;
    }

}
