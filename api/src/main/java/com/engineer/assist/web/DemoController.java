package com.engineer.assist.web;

import com.engineer.assist.entity.User;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.service.IUserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/demo")
@Api(tags = "测试，产品，作用类上")
public class DemoController{

    @Autowired
    private IUserService userService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有的产品信息")
    public Resp<List<User>> sat(){
        User user = new User();
        user.setCreatedBy("");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setIsActive(true);
        user.setUserName("asd");
        user.setUserPwd("asd");

        PageHelper.startPage(2,3);
        List<User> list = userService.list();
        return Resp.buildSuccess(list);
    }
}