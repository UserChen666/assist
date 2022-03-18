package com.engineer.assist.mapper;

import com.engineer.assist.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
