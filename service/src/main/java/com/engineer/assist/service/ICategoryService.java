package com.engineer.assist.service;

import com.engineer.assist.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.engineer.assist.entity.CategoryDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
public interface ICategoryService extends IService<Category> {

    public List<Category> list(Category category);
    Boolean create(CategoryDTO category);
}
