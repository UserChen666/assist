package com.engineer.assist.web;

import com.engineer.assist.entity.Category;
import com.engineer.assist.dto.CategoryDTO;
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
    @ResponseBody
    public Resp<Category> getCategoryById(@RequestParam Integer id) {
        return Resp.buildSuccess(iCategoryService.getById(id));
    }

    @GetMapping("/getByParentId")
    @ResponseBody
    public Resp<List<Category>> getByParentId(@RequestParam Integer id) {
        return Resp.buildSuccess(iCategoryService.getByParentId(id));
    }

    @GetMapping("/deleteById")
    @ResponseBody
    public Resp<Boolean> deleteById(@RequestParam Integer id) {
        return Resp.buildSuccess(iCategoryService.removeById(id));
    }

    @PostMapping("/create")
    @ResponseBody
    public Resp<Boolean> create(@RequestBody CategoryDTO category) {
        Boolean save = iCategoryService.create(category);
        return Resp.buildSuccess(save);
    }
    @PostMapping("/list")
    @ResponseBody
    public Resp<List<Category>> list(@RequestBody Category category){
        List<Category> list = iCategoryService.list(category);
        return Resp.buildSuccess(list);
    }
    @PostMapping("/update")
    @ResponseBody
    Resp<Boolean> updateCategory(@RequestBody Category category){
        boolean b = iCategoryService.updateById(category);
        return Resp.buildSuccess(b);
    }
}
