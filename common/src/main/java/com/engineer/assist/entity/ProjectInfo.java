package com.engineer.assist.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.engineer.assist.enumDTO.ProjectType;
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

    private ProjectType projectType;

    private String descripetion;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String createdBy;

    private Boolean isActive;

}
