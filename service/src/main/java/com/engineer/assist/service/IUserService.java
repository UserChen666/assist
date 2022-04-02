package com.engineer.assist.service;

import com.engineer.assist.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.engineer.assist.exception.ServerException;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
public interface IUserService extends IService<User> {

    String login(User user) throws ServerException;
}
