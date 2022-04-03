package com.engineer.assist.web;

import cn.hutool.crypto.SecureUtil;
import com.engineer.assist.entity.CurrentUserUtil;
import com.engineer.assist.entity.User;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.resp.Resp;
import com.engineer.assist.service.IUserService;
import com.engineer.assist.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/reg")
    @ResponseBody
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
    @ResponseBody
    public Resp<String> login(@RequestBody @Validated User user) throws ServerException {
        String login = userService.login(user);
        return Resp.buildSuccess(login);
    }

    @GetMapping("/detail")
    @ResponseBody
    public Resp<User> getAccount(HttpServletRequest request) throws ServerException {
        User u = userService.getAccount(CurrentUserUtil.currentToken());
        return Resp.buildSuccess(u);
    }

    @PostMapping("/logout")
    @ResponseBody
    public Resp<Boolean> loginOut(@RequestBody String token) {
        userService.loginOut(token);
        return Resp.buildSuccess(true);
    }
}
