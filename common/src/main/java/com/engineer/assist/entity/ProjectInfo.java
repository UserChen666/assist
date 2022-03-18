package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("project_info")
@ApiModel(value = "CategoryRel对象", description = "")
@Data
@Entity
public class ProjectInfo implements Serializable {

    @Id
    private Integer id;

    private String projectName;

    private String projectType;

    private String descripetion;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String createdBy;

    private Boolean isActive;

}
