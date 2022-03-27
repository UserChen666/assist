package com.engineer.assist.web;

import com.engineer.assist.entity.Category;
import com.engineer.assist.entity.CategoryDTO;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("/getById")
    public Resp<Category> getCategoryById(@RequestParam Integer id) {
        return Resp.buildSuccess(iCategoryService.getById(id));
    }

    @GetMapping("/deleteById")
    public Resp<Boolean> deleteById(@RequestParam Integer id) {
        return Resp.buildSuccess(iCategoryService.removeById(id));
    }

    @PostMapping("/create")
    public Resp<Boolean> create(@RequestBody CategoryDTO category) {
        Boolean save = iCategoryService.create(category);
        return Resp.buildSuccess(save);
    }
    @PostMapping("/list")
    public Resp<List<Category>> list(@RequestBody Category category){
        List<Category> list = iCategoryService.list(category);
        return Resp.buildSuccess(list);
    }
    @PostMapping("/update")
    Resp<Boolean> updateCategory(@RequestBody Category category){
        boolean b = iCategoryService.updateById(category);
        return Resp.buildSuccess(b);
    }
}
