package com.engineer.assist.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.engineer.assist.entity.CurrentUserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MybatisAutoFill implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(CurrentUserUtil.getUser() != null){
            setFieldValByName("createdBy", CurrentUserUtil.getName(),metaObject);
            setFieldValByName("updatedBy", CurrentUserUtil.getName(),metaObject);
            setFieldValByName("createDate", LocalDateTime.now(),metaObject);
            setFieldValByName("updateDate", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate", LocalDateTime.now(),metaObject);
    }
}
