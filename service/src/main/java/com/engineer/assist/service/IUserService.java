package com.engineer.assist.service;

import com.engineer.assist.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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

    boolean login(User user, HttpSession session);
}
