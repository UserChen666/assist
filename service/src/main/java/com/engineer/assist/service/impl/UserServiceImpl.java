package com.engineer.assist.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.engineer.assist.entity.CurrentUserUtil;
import com.engineer.assist.entity.User;
import com.engineer.assist.exception.ServerException;
import com.engineer.assist.mapper.UserMapper;
import com.engineer.assist.service.IUserService;
import com.engineer.assist.util.AssistUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public String login(User user) throws ServerException {
        String userName = user.getUserName();
        User entity = lambdaQuery().eq(User::getUserName, userName).eq(User::getUserPwd, SecureUtil.md5(user.getUserPwd())).one();

        if (entity == null) {
            throw new ServerException("account was wrong",HttpStatus.FORBIDDEN.value());
        }

        String uuid = AssistUtil.getUuid(false);

        sessionService.put(uuid,entity);

        return uuid;
    }

    @Override
    public User getAccount(String user) {
        try {
            return sessionService.get(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void loginOut(String token) {
        CurrentUserUtil.removeUserInfo();
        sessionService.remove(token);
    }
}
