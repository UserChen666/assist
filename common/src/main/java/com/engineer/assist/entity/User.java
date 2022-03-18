package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wbt
 * @since 2022-02-12
 */
@ApiModel(value = "User对象", description = "")
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotEmpty
    private String userName;
    @NotEmpty
    private String userPwd;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String createdBy;
    @TableLogic
    private Boolean isActive;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", userName=" + userName +
            ", userPwd=" + userPwd +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createdBy=" + createdBy +
            ", isActive=" + isActive +
        "}";
    }
}
