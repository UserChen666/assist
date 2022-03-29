package com.engineer.assist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.User;
import com.engineer.assist.mapper.UserMapper;
import com.engineer.assist.service.IUserService;
import com.engineer.assist.util.RestUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public boolean login(User user, HttpSession session) {
        Boolean login = false;
        String userName = user.getUserName();
        User entity = lambdaQuery().eq(User::getUserName, userName).getEntity();
        if (entity.getUserPwd().equals(user.getUserPwd())) {
            login = true;
            session.setAttribute("currentUser",entity);
        }
        return login;
    }
}
