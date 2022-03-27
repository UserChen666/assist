package com.engineer.assist.web;

import cn.hutool.crypto.SecureUtil;
import com.engineer.assist.entity.User;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.service.IUserService;
import com.engineer.assist.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/reg")
    public Resp<Boolean> reg(@RequestBody @Validated User user) {
        User newUser = new User();

        newUser.setUserPwd(SecureUtil.md5(user.getUserPwd()));
        newUser.setUserName(user.getUserName());
        userService.save(user);
        return Resp.buildSuccess();
    }

    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("ashgbosfsadf"));
    }

    @PostMapping("/login")
    public Resp<Boolean> login(@RequestBody @Validated User user, HttpSession session) {
        boolean login = userService.login(user);
        if (login) session.setAttribute("name", user.getUserName());
        return Resp.buildSuccess(login);
    }

    @PostMapping("/logout")
    public Resp<Boolean> loginOut(@RequestBody User user, HttpSession session) {
        RestUtil.removeUserInfo();
        session.setAttribute("name", null);
        return Resp.buildSuccess(true);
    }
}
