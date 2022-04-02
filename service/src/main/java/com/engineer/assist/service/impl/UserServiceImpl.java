package com.engineer.assist.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.User;
import com.engineer.assist.mapper.UserMapper;
import com.engineer.assist.service.IUserService;
import com.engineer.assist.util.AssistUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SessionService sessionService;

    @Override
    public String login(User user) {
        String userName = user.getUserName();
        User entity = lambdaQuery().eq(User::getUserName, userName).eq(User::getUserPwd, user.getUserPwd()).one();
        if (entity != null) {

            String uuid = AssistUtil.getUuid(false);

            sessionService.put(uuid,entity);

            return uuid;
        }

        return "";
    }
}
